package com.example.schedule_application.common.customException;

import org.springframework.http.HttpStatus;

public class ScheduleNotFoundException extends ServiceException {
    public ScheduleNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 일정을 찾을 수 없습니다.(존재하지 않는 ID)");
    }
}
