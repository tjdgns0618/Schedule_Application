package com.example.schedule_application.schedule.service;

import com.example.schedule_application.common.customException.ScheduleNotFoundException;
import com.example.schedule_application.schedule.dto.*;
import com.example.schedule_application.schedule.entity.Schedule;
import com.example.schedule_application.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<GetSchedulesResponse> findAll() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        return scheduleList.stream()
                .map(schedule -> new GetSchedulesResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthor(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public GetSchedulesResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleValidate(scheduleId);
        return new GetSchedulesResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    private Schedule scheduleValidate(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
    }

    @Transactional
    public UpdateScheduleResponse updateSchedule(UpdateScheduleRequest request, Long scheduleId) {
        Schedule schedule = scheduleValidate(scheduleId);
        schedule.updateSchedule(request.getTitle(), request.getContent(), request.getAuthor());
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleValidate(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }
}
