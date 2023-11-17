package com.example.carbook.web;

public class UsernameRequest {
    private String username;

    // Constructors, getters, and setters

    // Default constructor for Jackson deserialization
    public UsernameRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
