package com.example.schedule_application.schedule.dto;

import com.example.schedule_application.schedule.entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleAllDetailsResponse(Long id, String title, String content, String userName, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    public static ScheduleAllDetailsResponse from(Schedule schedule) {
        return new ScheduleAllDetailsResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(), schedule.getUser().getName(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
