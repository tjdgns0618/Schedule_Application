package com.example.schedule_application.comment.dto;

import com.example.schedule_application.comment.entity.Comment;

import java.time.LocalDateTime;

public record CommentAllDetailsResponse(Long id,
                                        String content,
                                        String scheduleTitle,
                                        String userName,
                                        LocalDateTime createdAt,
                                        LocalDateTime modifiedAt
) {
    public static CommentAllDetailsResponse from(Comment comment) {
        return new CommentAllDetailsResponse(comment.getId(),
                comment.getContent(),
                comment.getSchedule().getTitle(),
                comment.getUser().getName(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }
}
