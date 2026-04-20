package com.example.schedule_application.common.customException;

import org.springframework.http.HttpStatus;

public class NoPermissionException extends ServiceException {
    public NoPermissionException() {
        super(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");
    }
}
