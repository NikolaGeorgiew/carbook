package com.example.carbook.service.impl;

import com.example.carbook.model.dto.CarDetailDTO;
import com.example.carbook.model.dto.CarDetailForPricingDTO;
import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.model.entity.CarEntity;
import com.example.carbook.model.enums.CarTypeEnum;
import com.example.carbook.repo.CarRepository;
import com.example.carbook.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    @Override
    public Optional<CarDetailDTO> getCarDetail(Long id) {
        return carRepository
                .findById(id)
                .map(this::mapAsDetails);
    }


    @Override
    public Page<CarSummaryDTO> findAllByCarEnumAndIdNot(CarTypeEnum type, Long id, Pageable pageable) {
        return carRepository
                .findAllByCarEnumAndIdNot(type, id, pageable)
                .map(CarServiceImpl::mapAsSummary);
    }


    private CarDetailDTO mapAsDetails(CarEntity carEntity) {
        return new CarDetailDTO(
                carEntity.getId(),
                carEntity.getImageUrl(),
                carEntity.getMileage(),
                carEntity.getTransmission(),
                carEntity.getSeats(),
                carEntity.getLuggage(),
                carEntity.getFuel(),
                carEntity.getDescription(),
                carEntity.getBrand(),
                carEntity.getType());
    }

    private static CarSummaryDTO mapAsSummary(CarEntity carEntity) {
        return new CarSummaryDTO(
                carEntity.getId(), carEntity.getBrand(), carEntity.getType(), carEntity.getPriceForDay(), carEntity.getImageUrl(),
                carEntity.getPriceForHour(), carEntity.getPriceForMonth(), carEntity.getPriceForFuelSurcharges()
        );
    }
}
