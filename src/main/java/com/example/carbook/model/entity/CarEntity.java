package com.example.carbook.model.entity;

import com.example.carbook.model.enums.CarTypeEnum;
import com.example.carbook.model.enums.FuelEnum;
import com.example.carbook.model.enums.TransmissionEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "cars")
public class CarEntity extends BaseEntity{

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarTypeEnum type;

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

    @Column
    private String description;

    @Column
    private BigDecimal priceForDay;

    @Column
    private BigDecimal priceForHour;

    @Column
    private BigDecimal priceForMonth;

    private BigDecimal priceForFuelSurcharges;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public CarTypeEnum getType() {
        return type;
    }

    public void setType(CarTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getPriceForDay() {
        return priceForDay;
    }

    public void setPriceForDay(BigDecimal priceForDay) {
        this.priceForDay = priceForDay;
    }

    public BigDecimal getPriceForHour() {
        return priceForHour;
    }

    public void setPriceForHour(BigDecimal priceForHour) {
        this.priceForHour = priceForHour;
    }

    public BigDecimal getPriceForMonth() {
        return priceForMonth;
    }

    public void setPriceForMonth(BigDecimal priceForMonth) {
        this.priceForMonth = priceForMonth;
    }

    public BigDecimal getPriceForFuelSurcharges() {
        return priceForFuelSurcharges;
    }

    public void setPriceForFuelSurcharges(BigDecimal priceForFuelSurcharges) {
        this.priceForFuelSurcharges = priceForFuelSurcharges;
    }
}
