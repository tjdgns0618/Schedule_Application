package com.example.schedule_application.schedule.repository;

import com.example.schedule_application.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
