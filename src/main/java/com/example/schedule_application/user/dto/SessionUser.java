package com.example.schedule_application.user.dto;

import com.example.schedule_application.user.entity.User;

public record SessionUser(Long id, String password) {

    public static SessionUser from(User user) {
        return new SessionUser(user.getId(), user.getPassword());
    }
}
