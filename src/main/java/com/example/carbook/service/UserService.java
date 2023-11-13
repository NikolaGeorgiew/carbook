package com.example.carbook.service;

import com.example.carbook.model.dto.UserLoginBindingModel;
import com.example.carbook.model.dto.UserRegisterBindingModel;

public interface UserService {
    boolean register(UserRegisterBindingModel userRegisterBindingModel);

//    boolean login(UserLoginBindingModel userLoginBindingModel);
//
//    void logout();
}
