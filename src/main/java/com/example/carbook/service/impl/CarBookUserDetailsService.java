package com.example.carbook.service.impl;

import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.time.LocalDateTime;


public class CarBookUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CarBookUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));

        userEntity.setLastLogin(LocalDateTime.now());
        userRepository.save(userEntity);

        return mapDetails(userEntity);

    }
    private static UserDetails mapDetails(UserEntity userEntity) {
        return User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(CarBookUserDetailsService::mapAuthorities).toList())
                .build();
    }
    private static GrantedAuthority mapAuthorities(UserRoleEntity userRoleEntity) {
        return new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
    }

}
