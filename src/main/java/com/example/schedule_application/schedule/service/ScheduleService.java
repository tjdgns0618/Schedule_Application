package com.example.schedule_application.schedule.service;

import com.example.schedule_application.schedule.exception.ScheduleNotFoundException;
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

    private Schedule scheduleValidate(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
    }

    @Transactional
    public ScheduleAllDetailsResponse saveSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 왼쪽 타입을 오른쪽 인수를 통해서 만들겠다.
        return ScheduleAllDetailsResponse.from(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleAllDetailsResponse> findAll() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        return scheduleList.stream()
                .map(ScheduleAllDetailsResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public ScheduleAllDetailsResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleValidate(scheduleId);

        return ScheduleAllDetailsResponse.from(schedule);
    }

    @Transactional
    public ScheduleAllDetailsResponse updateSchedule(UpdateScheduleRequest request, Long scheduleId) {
        Schedule schedule = scheduleValidate(scheduleId);
        schedule.updateSchedule(request.getTitle(), request.getContent());
        return ScheduleAllDetailsResponse.from(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleValidate(scheduleId);
        scheduleRepository.deleteById(scheduleId);
    }
}
