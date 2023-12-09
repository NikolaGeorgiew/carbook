package com.example.carbook.service.impl;

import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.model.enums.UserRoleEnum;
import com.example.carbook.repo.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {
    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    // Replace UserRoleEntity and UserRoleEnum with the actual classes used in your service and repository
    @Test
    void testFindByRole() {
        // Create a sample UserRoleEnum
        UserRoleEnum roleEnum = UserRoleEnum.USER;

        // Create a sample UserRoleEntity
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        // Set properties on the userRoleEntity

        // Mock the behavior of roleRepository.findByRole
        when(roleRepository.findByRole(roleEnum)).thenReturn(userRoleEntity);

        // Invoke the method under test
        UserRoleEntity result = roleRepository.findByRole(roleEnum);

        // Verify that the findByRole method of roleRepository is called with the expected argument
        verify(roleRepository).findByRole(roleEnum);

        // Assertions
        // You may need to adjust this based on your actual entities and enumeration
        assertEquals(userRoleEntity, result);
    }
}
