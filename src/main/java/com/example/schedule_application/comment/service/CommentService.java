package com.example.schedule_application.comment.service;

import com.example.schedule_application.comment.dto.CommentAllDetailsResponse;
import com.example.schedule_application.comment.dto.CreateCommentRequest;
import com.example.schedule_application.comment.entity.Comment;
import com.example.schedule_application.comment.repository.CommentRepository;
import com.example.schedule_application.schedule.entity.Schedule;
import com.example.schedule_application.schedule.service.ScheduleService;
import com.example.schedule_application.user.entity.User;
import com.example.schedule_application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleService scheduleService;
    private final UserService userService;

    @Transactional
    public CommentAllDetailsResponse saveComment(Long userId, Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleService.validateSchedule(scheduleId);
        User user = userService.userValidation(userId);
        Comment comment = new Comment(request.content(), schedule, user);

        Comment savedComment = commentRepository.save(comment);

        return CommentAllDetailsResponse.from(savedComment);
    }

    @Transactional
    public List<CommentAllDetailsResponse> getAllComment() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map(CommentAllDetailsResponse::from).toList();
    }
}
