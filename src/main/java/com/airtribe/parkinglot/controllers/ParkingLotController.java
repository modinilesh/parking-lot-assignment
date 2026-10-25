package com.airtribe.parkinglot.controllers;

import com.airtribe.parkinglot.dto.AddFloorRequest;
import com.airtribe.parkinglot.dto.AddSpotRequest;
import com.airtribe.parkinglot.dto.CreateParkingLotRequest;
import com.airtribe.parkinglot.dto.ParkVehicleRequest;
import com.airtribe.parkinglot.dto.UnparkResponse;
import com.airtribe.parkinglot.entities.ParkingFloor;
import com.airtribe.parkinglot.entities.ParkingLot;
import com.airtribe.parkinglot.entities.ParkingSpot;
import com.airtribe.parkinglot.entities.Ticket;
import com.airtribe.parkinglot.enums.PaymentMethod;
import com.airtribe.parkinglot.enums.VehicleType;
import com.airtribe.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @PostMapping("/lots")
    public ParkingLot createParkingLot(@RequestBody CreateParkingLotRequest request) {
        return parkingLotService.createParkingLot(request);
    }

    @GetMapping("/lots/{lotId}/floors")
    public List<ParkingFloor> getFloors(@PathVariable Long lotId) {
        return parkingLotService.getFloors(lotId);
    }

    @PostMapping("/lots/{lotId}/floors")
    public ParkingFloor addFloor(@PathVariable Long lotId, @RequestBody AddFloorRequest request) {
        return parkingLotService.addFloor(lotId, request);
    }

    @PostMapping("/lots/{lotId}/floors/{floorId}/spots")
    public ParkingSpot addSpot(
            @PathVariable Long lotId,
            @PathVariable Long floorId,
            @RequestBody AddSpotRequest request
    ) {
        return parkingLotService.addSpot(lotId, floorId, request);
    }

    @GetMapping("/lots/{lotId}/spots")
    public List<ParkingSpot> getSpots(
            @PathVariable Long lotId,
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) VehicleType vehicleType
    ) {
        return parkingLotService.getSpots(lotId, available, vehicleType);
    }

    @PostMapping("/lots/{lotId}/park")
    public Ticket parkVehicle(@PathVariable Long lotId, @RequestBody ParkVehicleRequest request) {
        return parkingLotService.parkVehicle(lotId, request);
    }

    @PostMapping("/tickets/{ticketId}/unpark")
    public UnparkResponse unparkVehicle(
            @PathVariable Long ticketId,
            @RequestParam PaymentMethod paymentMethod,
            @RequestParam(defaultValue = "TIME_BASED") com.airtribe.parkinglot.enums.PricingStrategy pricingStrategy
    ) {
        return parkingLotService.unparkVehicle(ticketId, paymentMethod, pricingStrategy);
    }
}
