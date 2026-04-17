package com.example.schedule_application.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    private final String title;
    private final String content;
    private final String author;

    public CreateScheduleRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
