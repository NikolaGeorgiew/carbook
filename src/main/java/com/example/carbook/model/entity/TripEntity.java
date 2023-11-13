package com.example.carbook.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "trips")
public class TripEntity extends BaseEntity{

    @Column(name = "pick_up_location", nullable = false)
    @Size(min = 2, max = 100)
    private String pickUpLocation;

    @Column(name = "drop_off_location", nullable = false)
    @Size(min = 2, max = 100)
    private String dropOffLocation;

    @Column(name = "pick_up_date", nullable = false)
    @FutureOrPresent
    private LocalDate pickUpDate;

    @Column(name = "drop_off_date", nullable = false)
    @FutureOrPresent
    private LocalDate dropOffDate;

    @Column(name = "pick_up_time", nullable = false)
    @FutureOrPresent
    private LocalTime pickUpTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity addedBy;

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(LocalDate dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public UserEntity getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserEntity addedBy) {
        this.addedBy = addedBy;
    }
}
