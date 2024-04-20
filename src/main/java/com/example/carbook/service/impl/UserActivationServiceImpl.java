package com.example.carbook.service.impl;

import com.example.carbook.model.events.UserRegisteredEvent;
import com.example.carbook.service.EmailService;
import com.example.carbook.service.UserActivationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {
    private final EmailService emailService;

    public UserActivationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener(UserRegisteredEvent.class)
    @Override
    public void userRegistered(UserRegisteredEvent event) {
        //TODO: Add activation links
        emailService.sendRegistrationEmail(event.getUserEmail(), event.getUserNames());
    }
}
