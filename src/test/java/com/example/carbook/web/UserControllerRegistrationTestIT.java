package com.example.carbook.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerRegistrationTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/register")
                        .param("username", "user")
                        .param("email", "user@userov")
                        .param("password", "test")
                        .param("confirmPassword", "test")
                        .with(csrf())
        ).andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }
//    userEntity.setUsername("user");
//        userEntity.setEmail("user@email");
//        userEntity.setPassword("test");
//        userEntity.setRoles(List.of(adminRole,userRole));
}
