package com.guestbook.enums;

public enum UserRole {

    USER("USER"),

    ADMIN("ADMIN");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
