package com.example.schedule_application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(@NotBlank(message = "이름은 필수입니다.")
                                String name,

                                @NotBlank(message = "이메일은 필수이입니다.")
                                @Email(message = "이메일 형식에 맞지 않습니다.")
                                String email
) {

    public UpdateUserRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
