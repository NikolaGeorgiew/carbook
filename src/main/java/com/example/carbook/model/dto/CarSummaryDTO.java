package com.example.carbook.model.dto;

import com.example.carbook.model.enums.CarTypeEnum;

import java.math.BigDecimal;

public record CarSummaryDTO(
        Long id,
        String brand,
        CarTypeEnum type,
        BigDecimal priceForDay,
        String imageUrl,
        BigDecimal priceForHour,
        BigDecimal priceForMonth,
        BigDecimal priceForFuelSurcharges

) {
    public String summary() {
        return brand + " " + type;
    }
}
