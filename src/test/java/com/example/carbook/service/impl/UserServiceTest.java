package com.example.carbook.service.impl;

import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.model.enums.UserRoleEnum;
import com.example.carbook.repo.UserRepository;
import com.example.carbook.service.RoleService;
import com.example.carbook.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void makeAdmin_UserExistsAndNotAlreadyAdmin_ShouldMakeAdmin() {
        // Arrange
        String username = "testUser";
        UserEntity user = new UserEntity();
        user.setUsername(username);
        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(roleService.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        // Act
        userService.makeAdmin(username);

        // Assert
        assertTrue(user.getRoles().contains(adminRole));
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void makeAdmin_UserDoesNotExist_ShouldNotMakeAdmin() {
        // Arrange
        String username = "nonExistentUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        userService.makeAdmin(username);

        // Assert
        // Ensure that userRepository.save is never called
        verify(userRepository, never()).save(any());
    }

    @Test
    public void makeAdmin_UserIsAlreadyAdmin_ShouldNotMakeAdmin() {
        // Arrange
        String username = "adminUser";
        UserEntity user = new UserEntity();
        user.setUsername(username);
        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        user.getRoles().add(adminRole);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(roleService.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        // Act
        userService.makeAdmin(username);

        // Assert
        // Ensure that userRepository.save is never called
        verify(userRepository, never()).save(any());
    }

    @Test
    public void testRemoveAdmin_UserExistsAndIsAdmin_ShouldRemoveAdminRole() {
        // Arrange

        String username = "testUser";
        UserEntity user = new UserEntity();
        user.setUsername(username);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user));
        when(roleService.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        // Act
        userService.removeAdmin(username);

        // Assert
        assertTrue(user.getRoles().isEmpty()); // Assert that the admin role is removed
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRemoveAdmin_UserDoesNotExist_ShouldNotRemoveAdminRole() {
        // Arrange
        String username = "nonExistentUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        userService.removeAdmin(username);

        // Assert
        verify(userRepository, never()).save(any()); // Assert that userRepository.save is never called
    }

    @Test
    public void testIsAdmin_UserExistsAndIsAdmin_ShouldReturnTrue() {
        // Arrange
        String username = "adminUser";
        UserEntity user = new UserEntity();
        user.setUsername(username);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);
        user.setRoles(List.of(adminRole));

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(roleService.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        // Act
        boolean result = userService.isAdmin(username);

        // Assert
        assertTrue(result); // The user exists and is an admin, so should return true
    }

    @Test
    public void testIsAdmin_UserDoesNotExist_ShouldReturnFalse() {
        // Arrange
        String username = "nonExistentUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        boolean result = userService.isAdmin(username);

        // Assert
        assertFalse(result); // The user does not exist, so should return false
    }

    @Test
    public void testIsAdmin_UserExistsButNotAdmin_ShouldReturnFalse() {
        // Arrange
        String username = "regularUser";
        UserEntity user = new UserEntity();
        user.setUsername(username);

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(roleService.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole); // Simulate that the user is not an admin

        // Act
        boolean result = userService.isAdmin(username);

        // Assert
        assertFalse(result); // The user exists but is not an admin, so should return false
    }
    @Test
    public void testGetAllUsers() {
        // Arrange - Mock some user data
        UserEntity user1 = new UserEntity();
        user1.setId(1L);
        user1.setUsername("user1");

        UserEntity user2 = new UserEntity();
        user2.setId(2L);
        user2.setUsername("user2");

        List<UserEntity> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // Mock the behavior of userRepository.findAll()
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserEntity> result = userService.getAllUsers();

        // Assert
        assertEquals(2, result.size()); // Assuming there are two users in the list
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }


}
