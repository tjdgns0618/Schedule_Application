package com.example.schedule_application.schedule.entity;

import com.example.schedule_application.common.entity.BaseEntity;
import com.example.schedule_application.user.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateSchedule(String title,  String content) {
        this.title = title;
        this.content = content;
    }
}
