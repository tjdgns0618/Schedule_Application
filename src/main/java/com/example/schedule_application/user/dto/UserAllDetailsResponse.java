package com.example.schedule_application.user.dto;

import com.example.schedule_application.user.entity.User;

import java.time.LocalDateTime;

public record UserAllDetailsResponse(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    public static UserAllDetailsResponse from(User user) {
        return new UserAllDetailsResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }
}
