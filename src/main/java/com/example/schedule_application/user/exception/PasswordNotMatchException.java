package com.example.schedule_application.user.exception;

import com.example.schedule_application.common.customException.ServiceException;
import org.springframework.http.HttpStatus;

public class PasswordNotMatchException extends ServiceException {
    public PasswordNotMatchException() {
        super(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
    }
}
