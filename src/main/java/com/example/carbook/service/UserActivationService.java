package com.example.carbook.service;

import com.example.carbook.model.events.UserRegisteredEvent;

public interface UserActivationService {
    void userRegistered(UserRegisteredEvent event);
}
