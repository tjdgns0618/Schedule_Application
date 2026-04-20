package com.example.schedule_application.common.config;

import com.example.schedule_application.common.customException.ServiceException;
import com.example.schedule_application.common.dto.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// RestController 어노테이션처럼 ControllerAdvice + ResponseBody 가 합쳐진 형태이다.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 해당 클래스를 이용한 예외처리가 일어나면 하단의 상태코드와 메시지를 클라이언트에게 전달
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> handleServiceException(ServiceException e) {
        // dto로 전달을 해줘야 json으로 번역을 해준다. new ErrorResponse(String)
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getMessage()));
    }

    // JSON 형태에 맞지 않는 요청 바디를 보냈을때 400 BAD REQUEST 응답 핸들링
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        // dto로 전달을 해줘야 json으로 번역을 해준다. new ErrorResponse(String)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("JSON 문법에 맞게 요청을 작성해주세요."));
    }

    // bean validation의 예외처리는 MethodArgumentNotValidException를 이용하기 때문에 이를 핸들링한다.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("입력 값이 올바르지 않습니다.");
        // dto로 전달을 해줘야 json으로 번역을 해준다. new ErrorResponse(String)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
    }
}