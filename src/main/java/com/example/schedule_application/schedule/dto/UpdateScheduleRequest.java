package com.example.schedule_application.schedule.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateScheduleRequest(@NotBlank(message = "제목이 비어있을 수 없습니다.")
                                    String title,

                                    @NotBlank(message = "내용이 있어야 합니다.")
                                    String content
) {

    public UpdateScheduleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
