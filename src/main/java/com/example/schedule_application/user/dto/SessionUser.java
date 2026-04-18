package com.example.schedule_application.user.dto;

import com.example.schedule_application.user.entity.User;
import lombok.Getter;

@Getter
public class SessionUser {

    private final Long id;
    private final String password;

    public SessionUser(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public static SessionUser from(User user) {
        return new SessionUser(user.getId(), user.getPassword());
    }
}
