package com.example.carbook.service;

import com.example.carbook.model.dto.CarDetailDTO;
import com.example.carbook.model.dto.CarSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface CarService {
    Page<CarSummaryDTO> getAllCars(Pageable pageable);

    Optional<CarDetailDTO> getCarDetail(Long id);
}
