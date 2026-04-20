package com.example.schedule_application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "이름을 빈칸으로 하실 수 없습니다.")
    private final String name;

    @Email(message = "이메일 형식에 맞지 않습니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Min(value = 8, message = "비밀번호는 8글자 이상이어야 합니다.")
    private final String password;

    public SignUpRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
