package com.example.schedule_application.comment.controller;

import com.example.schedule_application.comment.dto.CommentAllDetailsResponse;
import com.example.schedule_application.comment.dto.CreateCommentRequest;
import com.example.schedule_application.comment.service.CommentService;
import com.example.schedule_application.user.dto.SessionUser;
import com.example.schedule_application.common.customException.LoginRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private void validateSessionUser(SessionUser sessionUser) {
        if(sessionUser == null) {
            throw new LoginRequiredException();
        }
    }

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentAllDetailsResponse> saveComment(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request
    ) {
        validateSessionUser(sessionUser);
        CommentAllDetailsResponse commentResponse = commentService.saveComment(sessionUser.id(), scheduleId, request);

        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentAllDetailsResponse>> getAllComment() {
        List<CommentAllDetailsResponse> commentAllResponses = commentService.getAllComment();

        return new ResponseEntity<>(commentAllResponses, HttpStatus.OK);
    }
}
