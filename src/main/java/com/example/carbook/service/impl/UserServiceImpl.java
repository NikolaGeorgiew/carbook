package com.example.carbook.service.impl;

import com.example.carbook.model.dto.UserRegisterBindingModel;
import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.model.enums.UserRoleEnum;
import com.example.carbook.model.events.UserRegisteredEvent;
import com.example.carbook.repo.UserRepository;
import com.example.carbook.service.RoleService;
import com.example.carbook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;
    private final ModelMapper modelMapper;

    //New
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, ModelMapper modelMapper, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return false;
        }
        boolean existsByUsernameOrEmail = userRepository
                .existsByUsernameOrEmail(userRegisterBindingModel.getUsername(), userRegisterBindingModel.getEmail());

        if (existsByUsernameOrEmail) {
            return false;
        }
        UserEntity user = modelMapper.map(userRegisterBindingModel,UserEntity.class);

        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setRole(UserRoleEnum.USER);

        user.setRoles(List.of(userRoleEntity));

        userRepository.save(user);

        //New
        applicationEventPublisher.publishEvent(new UserRegisteredEvent(
                "UserService", user.getEmail()
        ));
        //New

        return true;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void makeAdmin(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            UserRoleEntity adminRole = roleService.findByRole(UserRoleEnum.ADMIN);
            if (adminRole != null && !user.getRoles().contains(adminRole)) {
                user.getRoles().add(adminRole);
                userRepository.save(user);
            }
        }

    }

    @Override
    public void removeAdmin(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            UserRoleEntity adminRole = roleService.findByRole(UserRoleEnum.ADMIN);
            if (adminRole != null) {
                user.getRoles().remove(adminRole);
                userRepository.save(user);
            }
        }
    }

    @Override
    public boolean isAdmin(String username) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            UserRoleEntity adminRole = roleService.findByRole(UserRoleEnum.ADMIN);
            return adminRole != null && user.getRoles().contains(adminRole);
        }
        return false;
    }

}
