package com.example.carbook.web;

import com.example.carbook.model.dto.CarSummaryDTO;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PricingControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    // Replace CarSummaryDTO with the actual class used in your controller
    @Test
    public void testCarsEndpoint() throws Exception {
        // Create sample data
        List<CarSummaryDTO> carList = new ArrayList<>();
        // Add sample cars to the list

        // Create a Page object from the list
        Page<CarSummaryDTO> carPage = new PageImpl<>(carList);

        // Mock the behavior of carService.getAllCars
        when(carService.getAllCars(Mockito.any(Pageable.class))).thenReturn(carPage);

        // Perform the GET request to /pricing
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("pricing"))
                .andReturn();

        // You can add additional assertions based on the content of the response if needed
        // For example, you can check if the "cars" attribute is present in the model

        // Example:
        // String content = result.getResponse().getContentAsString();
        // assertThat(content).contains("Some expected content");
    }
}
