package com.example.schedule_application.common.customException;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// 커스텀 Exception들이 상속받은 예외클래스
@Getter
public class ServiceException extends RuntimeException {
    private final HttpStatus status;

    // 상태코드를 함께 보내주기 위해서 HttpStatus를 매개변수로 추가
    public ServiceException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
