package com.airtribe.parkinglot.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parking_lots")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parkingLotId;
    private String parkingLotName;
    private String location;
    private String parkingLotOwner;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingFloor> floors = new ArrayList<>();

    public ParkingLot(String parkingLotName, String location, String parkingLotOwner) {
        this.parkingLotName = parkingLotName;
        this.location = location;
        this.parkingLotOwner = parkingLotOwner;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
        floor.setParkingLot(this);
    }
}
