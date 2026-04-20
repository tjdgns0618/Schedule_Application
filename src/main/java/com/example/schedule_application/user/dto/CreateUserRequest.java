package com.example.schedule_application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotBlank(message = "이름을 빈칸으로 하실 수 없습니다.")
    private final String name;

    @Email(message = "이미 존재하는 이메일입니다.")
    private final String email;

    public CreateUserRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
