package com.example.schedule_application.schedule.dto;

import com.example.schedule_application.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleResponse(Long id, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
