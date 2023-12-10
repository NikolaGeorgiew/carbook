package com.example.carbook.service.impl;

import com.example.carbook.model.dto.AddTripDTO;
import com.example.carbook.model.entity.TripEntity;
import com.example.carbook.repo.CarRepository;
import com.example.carbook.repo.TripRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceImplTest {
    @Mock
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripService;

    @Test
    public void testCreateTrip() {
        // Arrange
        AddTripDTO addTripDTO = new AddTripDTO(1L,"asd","asd","12/12/2024", "12/12/2024","12:30am",1L);
        addTripDTO.setCarId(1L);  // Set a sample carId

        // Act
        tripService.createTrip(addTripDTO);

        // Assert
        verify(tripRepository, times(1)).save(any(TripEntity.class));

    }
}
