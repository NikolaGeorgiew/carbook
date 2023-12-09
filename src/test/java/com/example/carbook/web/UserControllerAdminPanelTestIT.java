package com.example.carbook.web;

import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerAdminPanelTestIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean  // Inject a mock of UserService
    private UserService userService;

    @Test
    void testShowAdminPanel() throws Exception {
        // Arrange - Mock some user data
        UserEntity user1 = new UserEntity();
        user1.setUsername("user1");

        UserEntity user2 = new UserEntity();
        user2.setUsername("user2");

        List<UserEntity> users = Arrays.asList(user1, user2);

        // Assume userService is autowired in your controller and returns the mocked users
        when(userService.getAllUsers()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/admin-panel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.view().name("admin-panel"));
    }


}

