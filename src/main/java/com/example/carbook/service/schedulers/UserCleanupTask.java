package com.example.carbook.service.schedulers;

import com.example.carbook.model.entity.UserEntity;
import com.example.carbook.repo.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserCleanupTask {


    private final UserRepository userRepository;

    public UserCleanupTask(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Run every day at midnight
    public void cleanupInactiveUsers() {
        LocalDateTime cutoffTime = LocalDateTime.now().minusYears(1);

        // Fetch users whose last login was more than 1 year ago
        List<UserEntity> inactiveUsers = userRepository.findByLastLoginBefore(cutoffTime);

        for (UserEntity user : inactiveUsers) {
            // Remove the user from roles and delete the user
            user.setRoles(List.of()); // Clear roles to avoid constraint violations
            userRepository.delete(user);
        }

        //log the number of deleted users
        System.out.println(inactiveUsers.size() + " inactive users deleted.");
    }
}
