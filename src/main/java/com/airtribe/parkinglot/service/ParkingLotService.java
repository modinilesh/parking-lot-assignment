package com.airtribe.parkinglot.service;

import com.airtribe.parkinglot.dto.AddFloorRequest;
import com.airtribe.parkinglot.dto.AddSpotRequest;
import com.airtribe.parkinglot.dto.CreateParkingLotRequest;
import com.airtribe.parkinglot.dto.ParkVehicleRequest;
import com.airtribe.parkinglot.dto.UnparkResponse;
import com.airtribe.parkinglot.entities.ParkingFloor;
import com.airtribe.parkinglot.entities.ParkingLot;
import com.airtribe.parkinglot.entities.ParkingSpot;
import com.airtribe.parkinglot.entities.Ticket;
import com.airtribe.parkinglot.entities.Vehicle;
import com.airtribe.parkinglot.enums.PaymentMethod;
import com.airtribe.parkinglot.enums.TicketStatus;
import com.airtribe.parkinglot.enums.VehicleType;
import com.airtribe.parkinglot.factories.PaymentFactory;
import com.airtribe.parkinglot.factories.PricingStrategyFactory;
import com.airtribe.parkinglot.repository.ParkingFloorRepository;
import com.airtribe.parkinglot.repository.ParkingLotRepository;
import com.airtribe.parkinglot.repository.TicketRepository;
import com.airtribe.parkinglot.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final ParkingFloorRepository parkingFloorRepository;
    private final VehicleRepository vehicleRepository;
    private final TicketRepository ticketRepository;

    @Transactional
    public ParkingLot createParkingLot(CreateParkingLotRequest request) {
        ParkingLot parkingLot = new ParkingLot(
                request.parkingLotName(),
                request.location(),
                request.parkingLotOwner()
        );
        return parkingLotRepository.save(parkingLot);
    }

    @Transactional(readOnly = true)
    public List<ParkingFloor> getFloors(Long lotId) {
        ParkingLot parkingLot = findParkingLot(lotId);
        parkingLot.getFloors().forEach(floor -> floor.getSpots().size());
        return parkingLot.getFloors();
    }

    @Transactional
    public ParkingFloor addFloor(Long lotId, AddFloorRequest request) {
        ParkingLot parkingLot = findParkingLot(lotId);
        ParkingFloor floor = new ParkingFloor(request.floorNumber());
        parkingLot.addFloor(floor);
        parkingLotRepository.save(parkingLot);
        return floor;
    }

    @Transactional
    public ParkingSpot addSpot(Long lotId, Long floorId, AddSpotRequest request) {
        ParkingFloor floor = findFloorInLot(lotId, floorId);
        ParkingSpot spot = new ParkingSpot(request.vehicleType());
        floor.addSpot(spot);
        parkingFloorRepository.save(floor);
        return spot;
    }

    @Transactional(readOnly = true)
    public List<ParkingSpot> getSpots(Long lotId, Boolean available, VehicleType vehicleType) {
        return findParkingLot(lotId).getFloors().stream()
                .flatMap(floor -> floor.getSpots().stream())
                .filter(spot -> available == null || spot.isAvailable() == available)
                .filter(spot -> vehicleType == null || spot.getVehicleType() == vehicleType)
                .toList();
    }

    @Transactional
    public synchronized Ticket parkVehicle(Long lotId, ParkVehicleRequest request) {
        ParkingLot parkingLot = findParkingLot(lotId);
        Vehicle vehicle = findOrCreateVehicle(request);
        if (ticketRepository.existsByVehicleAndStatus(vehicle, TicketStatus.PENDING)) {
            throw new IllegalArgumentException("Vehicle is already parked");
        }
        ParkingSpot spot = findFirstAvailableSpot(parkingLot, vehicle);
        spot.setAvailable(false);

        Ticket ticket = new Ticket();
        ticket.setVehicle(vehicle);
        ticket.setParkingSpot(spot);
        ticket.setEntryTime(LocalTime.now());
        ticket.setStatus(TicketStatus.PENDING);
        return ticketRepository.save(ticket);
    }

    @Transactional
    public UnparkResponse unparkVehicle(
            Long ticketId,
            PaymentMethod paymentMethod,
            com.airtribe.parkinglot.enums.PricingStrategy pricingStrategy
    ) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));
        if (ticket.getStatus() != TicketStatus.PENDING) {
            throw new IllegalArgumentException("Ticket is not active");
        }

        ticket.setExitTime(LocalTime.now());
        double amount = PricingStrategyFactory.createPricingStrategy(pricingStrategy).calculatePrice(ticket);
        PaymentFactory.createPayment(paymentMethod).pay(amount);

        ticket.getParkingSpot().setAvailable(true);
        ticket.setStatus(TicketStatus.PAID);
        ticketRepository.save(ticket);
        return new UnparkResponse(ticket.getId(), amount, ticket.getStatus(), "PAYMENT_DONE");
    }

    private ParkingLot findParkingLot(Long lotId) {
        return parkingLotRepository.findById(lotId)
                .orElseThrow(() -> new IllegalArgumentException("Parking lot not found"));
    }

    private ParkingFloor findFloorInLot(Long lotId, Long floorId) {
        ParkingFloor floor = parkingFloorRepository.findById(floorId)
                .orElseThrow(() -> new IllegalArgumentException("Parking floor not found"));
        if (!floor.getParkingLot().getParkingLotId().equals(lotId)) {
            throw new IllegalArgumentException("Floor does not belong to this parking lot");
        }
        return floor;
    }

    private Vehicle findOrCreateVehicle(ParkVehicleRequest request) {
        return vehicleRepository.findByVehicleNumber(request.vehicleNumber())
                .orElseGet(() -> {
                    Vehicle vehicle = new Vehicle(request.vehicleType(), request.vehicleNumber(), request.ownerName());
                    vehicle.setOwnerPhone(request.ownerPhone());
                    return vehicleRepository.save(vehicle);
                });
    }

    private ParkingSpot findFirstAvailableSpot(ParkingLot parkingLot, Vehicle vehicle) {
        return parkingLot.getFloors().stream()
                .flatMap(floor -> floor.getSpots().stream())
                .filter(spot -> spot.canPark(vehicle))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No available spot for this vehicle type"));
    }
}
