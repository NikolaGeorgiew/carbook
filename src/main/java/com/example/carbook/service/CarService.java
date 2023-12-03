package com.example.carbook.service;

import com.example.carbook.model.dto.CarSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CarService {
    Page<CarSummaryDTO> getAllCars(Pageable pageable);
}
