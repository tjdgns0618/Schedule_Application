package com.example.schedule_application.user.exception;

import com.example.schedule_application.common.customException.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ServiceException {
    public DuplicateEmailException() {
        super(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다. 다른 이메일로 입력해주세요.");
    }
}
