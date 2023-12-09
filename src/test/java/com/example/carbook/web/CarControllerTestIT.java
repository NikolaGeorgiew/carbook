package com.example.carbook.web;

import com.example.carbook.model.dto.CarDetailDTO;
import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.model.enums.CarTypeEnum;
import com.example.carbook.model.enums.FuelEnum;
import com.example.carbook.model.enums.TransmissionEnum;
import com.example.carbook.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService carService;


    @Test
    void testCarsPage() throws Exception {
        // Create sample data for the car page
        List<CarSummaryDTO> carList = new ArrayList<>();
        // Add sample CarSummaryDTO objects to the list

        // Mock the behavior of carService.getAllCars to return the sample data
        when(carService.getAllCars(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(carList));

        // Perform an HTTP GET request to "/car"
        ResultActions result = mockMvc.perform(get("/car"));

        // Verify the response status is OK (HTTP 200)
        result.andExpect(status().isOk());

        // Verify that the view name is "car"
        result.andExpect(view().name("car"));

        // Verify that the "cars" attribute is added to the model with the correct data
        result.andExpect(model().attribute("cars", hasProperty("content", is(carList))));
    }
    @Test
    void testCarDetailsPage() throws Exception {
        // Create sample data for the car details page
        Long carId = 1L;
        CarDetailDTO carDetail = new CarDetailDTO(carId,"", 100, TransmissionEnum.MANUAL, 4, 4, FuelEnum.DIESEL,"description", "Mercedes Bez", CarTypeEnum.COUPE);
        Page<CarSummaryDTO> allCarsExcludingOne = new PageImpl<>(new ArrayList<>());

        // Mock the behavior of carService.getCarDetail to return the sample data
        when(carService.getCarDetail(carId)).thenReturn(Optional.of(carDetail));
        // Mock the behavior of carService.findAllByCarEnumAndIdNot to return the sample data
        when(carService.findAllByCarEnumAndIdNot(Mockito.eq(carDetail.type()), Mockito.eq(carId), Mockito.any()))
                .thenReturn(allCarsExcludingOne);

        // Perform an HTTP GET request to "/car/{id}"
        ResultActions result = mockMvc.perform(get("/car/{id}", carId));

        // Verify the response status is OK (HTTP 200)
        result.andExpect(status().isOk());

        // Verify that the view name is "car-single"
        result.andExpect(view().name("car-single"));

        // Verify that the "car" attribute is added to the model with the correct data
        result.andExpect(model().attribute("car", is(carDetail)));
        // Verify that the "allCars" attribute is added to the model with the correct data
        result.andExpect(model().attribute("allCars", hasProperty("content", is(allCarsExcludingOne.getContent()))));
    }
}
