package com.example.schedule_application.user.dto;

import com.example.schedule_application.user.entity.User;

public record LoginResponse(Long id, String name, String email) {
    public static LoginResponse from(User user) {
        return new LoginResponse(user.getId(), user.getName(), user.getEmail());
    }
}
