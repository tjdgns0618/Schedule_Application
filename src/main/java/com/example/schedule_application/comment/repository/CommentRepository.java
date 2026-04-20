package com.example.schedule_application.comment.repository;

import com.example.schedule_application.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
