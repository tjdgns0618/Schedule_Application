package com.example.schedule_application.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {
    @NotBlank(message = "제목이 비어있을 수 없습니다.")
    private final String title;
    @NotBlank(message = "내용이 있어야 합니다.")
    private final String content;
    @NotBlank(message = "작성자명을 입력해야 합니다.")
    private final String author;

    public CreateScheduleRequest(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
