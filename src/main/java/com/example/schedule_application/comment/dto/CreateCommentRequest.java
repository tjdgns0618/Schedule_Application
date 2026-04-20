package com.example.schedule_application.comment.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(@NotBlank(message = "내용은 필수입니다.") String content) {

    public CreateCommentRequest(String content) {
        this.content = content;
    }
}
