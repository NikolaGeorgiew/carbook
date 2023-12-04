package com.example.carbook.model.dto;

import com.example.carbook.model.enums.CarTypeEnum;
import com.example.carbook.model.enums.FuelEnum;
import com.example.carbook.model.enums.TransmissionEnum;

public record CarDetailDTO(
        Long id,
        String imageUrl,
        Integer mileage,
        TransmissionEnum transmission,
        Integer seats,
        Integer luggage,
        FuelEnum fuel,
        String description,
        String brand,
        CarTypeEnum type
) {
    public String summary() {
        return brand + " " + type;
    }
}
