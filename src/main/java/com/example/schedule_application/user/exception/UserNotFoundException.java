package com.example.schedule_application.user.exception;

import com.example.schedule_application.common.customException.ServiceException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다.(존재하지 않는 ID)");
    }
}
