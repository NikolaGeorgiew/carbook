package com.example.carbook.service.impl;

import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.model.entity.CarEntity;
import com.example.carbook.repo.CarRepository;
import com.example.carbook.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Page<CarSummaryDTO> getAllCars(Pageable pageable) {
        return carRepository
                .findAll(pageable)
                .map(CarServiceImpl::mapAsSummary);
    }

    private static CarSummaryDTO mapAsSummary(CarEntity carEntity) {
        return new CarSummaryDTO(
                carEntity.getId(), carEntity.getBrand(), carEntity.getType(), carEntity.getPriceForDay(), carEntity.getImageUrl()
        );
    }
}
