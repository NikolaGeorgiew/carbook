package com.example.carbook.web;

import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarService carService;

    @Test
    void testIndexPage() throws Exception {
        // Create sample data for the index page
        List<CarSummaryDTO> carList = new ArrayList<>();
        // Add sample CarSummaryDTO objects to the list

        // Mock the behavior of carService.getAllCars to return the sample data
        when(carService.getAllCars(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(carList));

        // Perform an HTTP GET request to "/"
        ResultActions result = mockMvc.perform(get("/"));

        // Verify the response status is OK (HTTP 200)
        result.andExpect(status().isOk());

        // Verify that the view name is "index"
        result.andExpect(view().name("index"));

        // Verify that the "cars" attribute is added to the model with the correct data
        result.andExpect(model().attribute("cars", hasProperty("content", is(carList))));
    }
}
