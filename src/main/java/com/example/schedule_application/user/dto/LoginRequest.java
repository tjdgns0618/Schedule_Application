package com.example.schedule_application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "이메일은 필수입니다.") @Email(message = "올바른 이메일 형식이 아닙니다.") String email,
                           @NotBlank(message = "비밀번호는 필수입니다.") @Min(value = 8, message = "비밀번호는 8자 이상이어야합니다.") String password
) {

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
