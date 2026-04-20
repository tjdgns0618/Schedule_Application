package com.example.schedule_application.user.exception;

import com.example.schedule_application.common.customException.ServiceException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceException {

    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
