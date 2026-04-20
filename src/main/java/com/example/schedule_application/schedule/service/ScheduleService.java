package com.example.schedule_application.schedule.service;

import com.example.schedule_application.schedule.exception.NoPermissionException;
import com.example.schedule_application.schedule.exception.ScheduleNotFoundException;
import com.example.schedule_application.schedule.dto.*;
import com.example.schedule_application.schedule.entity.Schedule;
import com.example.schedule_application.schedule.repository.ScheduleRepository;
import com.example.schedule_application.user.entity.User;
import com.example.schedule_application.user.exception.UserNotFoundException;
import com.example.schedule_application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    private Schedule validateSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
    }

    private void validateOwner(Schedule schedule, Long userId) {
        if(!schedule.getUser().getId().equals(userId)) {
            throw new NoPermissionException();
        }
    }

    @Transactional
    public ScheduleAllDetailsResponse saveSchedule(Long userId, CreateScheduleRequest request) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), user);

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
        Schedule schedule = validateSchedule(scheduleId);

        return ScheduleAllDetailsResponse.from(schedule);
    }

    @Transactional
    public ScheduleAllDetailsResponse updateSchedule(Long userId, UpdateScheduleRequest request, Long scheduleId) {
        Schedule schedule = validateSchedule(scheduleId);
        validateOwner(schedule, userId);
        schedule.updateSchedule(request.getTitle(), request.getContent());
        return ScheduleAllDetailsResponse.from(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, Long userId) {
        Schedule schedule = validateSchedule(scheduleId);
        validateOwner(schedule, userId);
        scheduleRepository.deleteById(scheduleId);
    }
}
