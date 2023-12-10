package com.example.carbook.service.impl;

import com.example.carbook.model.dto.CarDetailDTO;
import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.model.entity.CarEntity;
import com.example.carbook.model.enums.CarTypeEnum;
import com.example.carbook.repo.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void testGetAllCars() {
        // Create sample data
        List<CarEntity> carEntities = new ArrayList<>();
        // Add sample car entities to the list

        // Create a Page object from the list
        Page<CarEntity> carPage = new PageImpl<>(carEntities);

        // Mock the behavior of carRepository.findAll
        when(carRepository.findAll(Mockito.any(Pageable.class))).thenReturn(carPage);

        // Invoke the method under test
        Page<CarSummaryDTO> result = carService.getAllCars(Pageable.unpaged());

        // Assertions
        assertEquals(carPage.getTotalElements(), result.getTotalElements());
    }
    @Test
    void testGetCarDetailWhenCarExists() {
        // Create a sample car entity
        CarEntity carEntity = new CarEntity();
        // Set properties on the car entity

        // Mock the behavior of carRepository.findById
        when(carRepository.findById(1L)).thenReturn(Optional.of(carEntity));

        // Invoke the method under test
        Optional<CarDetailDTO> result = carService.getCarDetail(1L);

        // Assertions
        assertTrue(result.isPresent());
    }

    @Test
    void testGetCarDetailWhenCarDoesNotExist() {
        // Mock the behavior of carRepository.findById for a non-existent car
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        // Invoke the method under test
        Optional<CarDetailDTO> result = carService.getCarDetail(1L);

        // Assertions
        assertFalse(result.isPresent());
    }
    @Test
    void testFindAllByCarEnumAndIdNot() {
        // Create sample data
        List<CarEntity> carEntities = new ArrayList<>();
        // Add sample car entities to the list

        // Create a Page object from the list
        Page<CarEntity> carPage = new PageImpl<>(carEntities);

        // Mock the behavior of carRepository.findAllByCarEnumAndIdNot
        when(carRepository.findAllByCarEnumAndIdNot(Mockito.any(CarTypeEnum.class), Mockito.anyLong(), Mockito.any(Pageable.class)))
                .thenReturn(carPage);

        // Invoke the method under test
        Page<CarSummaryDTO> result = carService.findAllByCarEnumAndIdNot(CarTypeEnum.SEDAN, 1L, Pageable.unpaged());

        // Assertions
        assertEquals(carPage.getTotalElements(), result.getTotalElements());
    }
    @Test
    void testMapAsDetails() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Create a sample car entity
        CarEntity carEntity = new CarEntity();
        // Set properties on the car entity

        // Get the mapAsDetails method using reflection
        Method mapAsDetailsMethod = CarServiceImpl.class.getDeclaredMethod("mapAsDetails", CarEntity.class);
        mapAsDetailsMethod.setAccessible(true);  // Make the private method accessible

        // Invoke the method under test
        CarDetailDTO result = (CarDetailDTO) mapAsDetailsMethod.invoke(carService, carEntity);

        // Assertions
        assertEquals(carEntity.getId(), result.id());
        assertEquals(carEntity.getImageUrl(), result.imageUrl());
        assertEquals(carEntity.getMileage(), result.mileage());
        assertEquals(carEntity.getTransmission(), result.transmission());
        assertEquals(carEntity.getSeats(), result.seats());
        assertEquals(carEntity.getLuggage(), result.luggage());
        assertEquals(carEntity.getFuel(), result.fuel());
        assertEquals(carEntity.getDescription(), result.description());
        assertEquals(carEntity.getBrand(), result.brand());
        assertEquals(carEntity.getType(), result.type());
    }
    @Test
    void testMapAsSummary() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Create a sample car entity
        CarEntity carEntity = new CarEntity();
        // Set properties on the car entity

        // Get the mapAsSummary method using reflection
        Method mapAsSummaryMethod = CarServiceImpl.class.getDeclaredMethod("mapAsSummary", CarEntity.class);
        mapAsSummaryMethod.setAccessible(true);  // Make the private method accessible

        // Invoke the method under test
        CarSummaryDTO result = (CarSummaryDTO) mapAsSummaryMethod.invoke(null, carEntity);

        // Assertions
        assertEquals(carEntity.getId(), result.id());
        assertEquals(carEntity.getBrand(), result.brand());
        assertEquals(carEntity.getType(), result.type());
        assertEquals(carEntity.getPriceForDay(), result.priceForDay());
        assertEquals(carEntity.getImageUrl(), result.imageUrl());
        assertEquals(carEntity.getPriceForHour(), result.priceForHour());
        assertEquals(carEntity.getPriceForMonth(), result.priceForMonth());
        assertEquals(carEntity.getPriceForFuelSurcharges(), result.priceForFuelSurcharges());
    }
}
