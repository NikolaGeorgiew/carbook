package com.example.carbook.web;

import com.example.carbook.model.dto.AddTripDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TripControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowAddTripForm() throws Exception {
        Long carId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/add-trip/{id}", carId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/add-trip"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("addTripDTO"))
                .andExpect(MockMvcResultMatchers.model().attribute("addTripDTO", Matchers.instanceOf(AddTripDTO.class)))
                .andExpect(MockMvcResultMatchers.model().attribute("addTripDTO", Matchers.hasProperty("carId", Matchers.equalTo(carId))));
    }
}
