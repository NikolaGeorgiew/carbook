package com.example.carbook.service;

import com.example.carbook.model.dto.UserLoginBindingModel;
import com.example.carbook.model.dto.UserRegisterBindingModel;
import com.example.carbook.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);
    List<UserEntity> getAllUsers();

    void makeAdmin(String username);

    void removeAdmin(String username);

    boolean isAdmin(String username);

//    boolean login(UserLoginBindingModel userLoginBindingModel);
//
//    void logout();
}
