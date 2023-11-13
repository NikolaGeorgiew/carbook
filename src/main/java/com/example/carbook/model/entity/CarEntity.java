package com.example.carbook.model.entity;

import com.example.carbook.model.enums.FuelEnum;
import com.example.carbook.model.enums.TransmissionEnum;
import jakarta.persistence.*;



@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity{

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Integer mileage;

    @Column
    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    @Column
    private Integer seats;

    @Column
    private Integer luggage;

    @Column
    @Enumerated(EnumType.STRING)
    private FuelEnum fuel;

    @Column(columnDefinition = "TEXT")
    private String description;

    //TODO : implement features
    //private List<String> features;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getLuggage() {
        return luggage;
    }

    public void setLuggage(Integer luggage) {
        this.luggage = luggage;
    }

    public FuelEnum getFuel() {
        return fuel;
    }

    public void setFuel(FuelEnum fuel) {
        this.fuel = fuel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
