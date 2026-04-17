package com.example.schedule_application.schedule.entity;

import com.example.schedule_application.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(length = 25, nullable = false)
    private String author;

    public Schedule(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void updateSchedule(String title,  String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
