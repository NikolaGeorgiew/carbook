package com.example.carbook.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AboutControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAboutPage() throws Exception {
        ResultActions result = mockMvc.perform(get("/about"));

        // Verify the response status is OK (HTTP 200)
        result.andExpect(status().isOk());

        // Verify that the view name is "/about"
        result.andExpect(view().name("/about"));

        // Optionally, you can further assert the content of the response if needed
        // For example, if your view renders dynamic content, you might check for specific text
        // result.andExpect(content().string(containsString("Some expected content")));
    }
}
