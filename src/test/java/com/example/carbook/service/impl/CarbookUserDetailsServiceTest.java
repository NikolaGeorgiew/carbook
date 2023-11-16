package com.example.carbook.service.impl;

import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.model.enums.UserRoleEnum;
import com.example.carbook.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarbookUserDetailsServiceTest {

    private CarBookUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new CarBookUserDetailsService(mockUserRepository);
    }
    @Test
    void testUserNotFoundException() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> serviceToTest.loadUserByUsername("user"));
    }

    @Test
    void testUserFoundException() {
        //Arrange
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByUsername(testUserEntity.getUsername()))
                .thenReturn(Optional.of(testUserEntity));
        //Act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getUsername());

        //Assert
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUserEntity.getUsername(), userDetails.getUsername(), "Username is not mapped");

        Assertions.assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());

        Assertions.assertTrue(
                containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN), "The user is not admin");
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER), "The user is not user");
    }

    private  UserEntity createTestUser() {
        UserEntity userEntity = new UserEntity();

        UserRoleEntity adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);


        userEntity.setUsername("user");
        userEntity.setEmail("user@email");
        userEntity.setPassword("test");
        userEntity.setRoles(List.of(adminRole,userRole));

        return userEntity;
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

}
