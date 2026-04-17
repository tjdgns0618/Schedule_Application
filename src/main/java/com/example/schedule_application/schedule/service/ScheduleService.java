package com.example.schedule_application.schedule.service;

import com.example.schedule_application.schedule.dto.CreateScheduleRequest;
import com.example.schedule_application.schedule.dto.CreateScheduleResponse;
import com.example.schedule_application.schedule.entity.Schedule;
import com.example.schedule_application.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse saveSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), request.getAuthor());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }
}
