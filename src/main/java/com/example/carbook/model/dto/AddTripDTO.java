package com.example.carbook.model.dto;

import com.example.carbook.model.annotation.StringDateFutureOrPresent;
import com.example.carbook.model.annotation.StringTimeFutureOrPresent;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddTripDTO{
    private Long id;
    @NotEmpty @Size(min = 2, max = 100)
    private String pickUpLocation;
    @NotEmpty @Size(min = 2, max = 100)
    private String dropOffLocation;
    @NotEmpty @StringDateFutureOrPresent
    private String pickUpDate;
    @NotEmpty @StringDateFutureOrPresent
    private String dropOffDate;
    @NotEmpty @StringTimeFutureOrPresent
    private String pickUpTime;

    public AddTripDTO(Long id, String pickUpLocation, String dropOffLocation, String pickUpDate, String dropOffDate, String pickUpTime) {
        this.id = id;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.pickUpTime = pickUpTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(String dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
    public static AddTripDTO empty() {
        return new AddTripDTO(null, null, null,null,null,null);
    }
}