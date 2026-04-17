package com.example.schedule_application.common.customException;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
        super("존재하지 않는 일정 번호입니다.");
    }
}
