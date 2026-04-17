package com.example.schedule_application.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private final String name;
    private final String email;

    public UpdateUserRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
