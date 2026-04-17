package com.example.schedule_application.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private final String title;
    private final String content;
    private final String author;

    public UpdateScheduleRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
