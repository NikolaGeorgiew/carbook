package com.example.carbook.model.enums;

public enum UserRoleEnum {
    ADMIN("Admin"),
    USER("User");

    private final String displayValue;

    UserRoleEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
