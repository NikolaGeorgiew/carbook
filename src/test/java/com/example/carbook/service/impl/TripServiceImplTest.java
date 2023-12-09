package com.example.carbook.service.impl;

import com.example.carbook.model.dto.AddTripDTO;
import com.example.carbook.model.entity.CarEntity;
import com.example.carbook.model.entity.TripEntity;
import com.example.carbook.repo.CarRepository;
import com.example.carbook.repo.TripRepository;
import com.example.carbook.service.TripService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceImplTest {
    @Mock
    private TripRepository tripRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private TripServiceImpl tripService;

    @Test
    public void testCreateTrip() {
        // Arrange
        AddTripDTO addTripDTO = new AddTripDTO(1L,"asd","asd","12/12/2024", "12/12/2024","12:30am",1L);
        addTripDTO.setCarId(1L);  // Set a sample carId
        // Set other properties of addTripDTO if needed

        // Act
        tripService.createTrip(addTripDTO);

        // Assert
        verify(tripRepository, times(1)).save(any(TripEntity.class));

        // You can add additional assertions based on your logic in the createTrip method
    }
}
