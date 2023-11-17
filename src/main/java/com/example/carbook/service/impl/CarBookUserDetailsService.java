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
import java.util.List;


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

        UserDetails userDetails = mapDetails(userEntity);

//        UserDetails userDetails = userRepository
//                .findByUsername(username)
//                .map(CarBookUserDetailsService::mapDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));

        return userDetails;

    }
    private static UserDetails mapDetails(UserEntity userEntity) {
        UserDetails build = User
                .withUsername(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(userEntity.getRoles().stream().map(CarBookUserDetailsService::mapAuthorities).toList())
                .build();
        return build;
    }
    private static GrantedAuthority mapAuthorities(UserRoleEntity userRoleEntity) {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name());
        return simpleGrantedAuthority;
    }

    // Add a method to get users who haven't been logged in for more than a year
//    public List<UserEntity> findInactiveUsers() {
//        LocalDateTime oneMinAgo = LocalDateTime.now().minusMinutes(1);
//        return userRepository.findByLastLoginBefore(oneMinAgo);
//    }
//    // Add a method to delete inactive users
//    public void deleteInactiveUsers(List<UserEntity> inactiveUsers) {
//        userRepository.deleteAll(inactiveUsers);
//    }

}
