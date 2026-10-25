package com.airtribe.parkinglot.entities;

import com.airtribe.parkinglot.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parking_spots")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private boolean isAvailable = true;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parking_floor_id", nullable = false)
    private ParkingFloor parkingFloor;

    public ParkingSpot(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        this.isAvailable = true;
    }

    public boolean canPark(Vehicle vehicle) {
        return this.isAvailable && this.vehicleType == vehicle.getVehicleType();
    }
}
