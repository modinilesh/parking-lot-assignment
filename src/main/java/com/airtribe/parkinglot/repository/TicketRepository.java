package com.airtribe.parkinglot.repository;

import com.airtribe.parkinglot.entities.Ticket;
import com.airtribe.parkinglot.entities.Vehicle;
import com.airtribe.parkinglot.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    boolean existsByVehicleAndStatus(Vehicle vehicle, TicketStatus status);
}
