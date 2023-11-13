package com.example.carbook.service.impl;

import com.example.carbook.model.dto.UserLoginBindingModel;
import com.example.carbook.model.dto.UserRegisterBindingModel;
import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.model.entity.UserRoleEntity;
import com.example.carbook.model.enums.UserRoleEnum;
import com.example.carbook.repo.UserRepository;
import com.example.carbook.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    //private final LoggedUser loggedUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        //this.loggedUser = loggedUser;
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

        UserEntity user = new UserEntity();

        user.setEmail(userRegisterBindingModel.getEmail());
        user.setUsername(userRegisterBindingModel.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));

//        if (userRepository.count() == 0) {
//            user.setRole(UserRoleEnum.ADMIN);
//        } else  {
//            user.setRole(UserRoleEnum.USER);
//        }

        userRepository.save(user);

        return true;
    }

//    @Override
//    public boolean login(UserLoginBindingModel userLoginBindingModel) {
//        String username = userLoginBindingModel.getUsername();
//        UserEntity user = userRepository.findByUsername(username);
//
//        if (user != null && passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword())) {
//            loggedUser.login(username);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void logout() {
//        this.loggedUser.logout();
//    }
}
