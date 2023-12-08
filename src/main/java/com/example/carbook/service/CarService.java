package com.example.carbook.service;

import com.example.carbook.model.dto.CarDetailDTO;
import com.example.carbook.model.dto.CarDetailForPricingDTO;
import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.model.enums.CarTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface CarService {
    Page<CarSummaryDTO> getAllCars(Pageable pageable);

    Optional<CarDetailDTO> getCarDetail(Long id);

//    Page<CarSummaryDTO> getAllCarsExcludingOne(Pageable pageable, Long id);

    Page<CarSummaryDTO> findAllByCarEnumAndIdNot(CarTypeEnum type, Long id, Pageable pageable);
}
