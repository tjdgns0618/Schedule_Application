package com.example.schedule_application.common.customException;

import org.springframework.http.HttpStatus;

public class LoginRequiredException extends ServiceException {
    public LoginRequiredException() {
        super(HttpStatus.UNAUTHORIZED, "로그인이 필요한 서비스입니다.");
    }
}
