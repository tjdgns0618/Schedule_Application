package com.example.schedule_application.schedule.service;

import com.example.schedule_application.common.customException.NoPermissionException;
import com.example.schedule_application.common.customException.ScheduleNotFoundException;
import com.example.schedule_application.schedule.dto.*;
import com.example.schedule_application.schedule.entity.Schedule;
import com.example.schedule_application.schedule.repository.ScheduleRepository;
import com.example.schedule_application.user.entity.User;
import com.example.schedule_application.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    /**
     * 일정이 존재하는지 검사
     *
     * @param scheduleId 검사할 일정 고유 번호
     * @return DB에서 찾은 일정
     */
    @Transactional
    public Schedule validateSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(ScheduleNotFoundException::new);
    }

    /**
     * 작성자가 본인인지 검사
     *
     * @param schedule 검사할 일정
     * @param userId   클라이언트 유저 고유 번호
     */
    @Transactional
    public void validateOwner(Schedule schedule, Long userId) {
        if (!schedule.getUser().getId().equals(userId)) {
            throw new NoPermissionException();
        }
    }

    /**
     * 일정 생성
     *
     * @param userId  일정 생성하는 유저 고유 번호
     * @param request 생성할 일정의 데이터
     * @return 생성한 일정의 데이터
     */
    @Transactional
    public ScheduleAllDetailsResponse saveSchedule(Long userId, CreateScheduleRequest request) {
        User user = userService.userValidation(userId);
        Schedule schedule = new Schedule(request.title(), request.content(), user);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // 왼쪽 타입을 오른쪽 인수를 통해서 만들겠다. 정적 팩토리 메서드 패턴
        return ScheduleAllDetailsResponse.from(savedSchedule);
    }

    /**
     * 모든 일정을 조회
     *
     * @return 모든 일정의 데이터 리스트
     */
    @Transactional(readOnly = true)
    public List<ScheduleAllDetailsResponse> findAllSchedule() {
        List<Schedule> scheduleList = scheduleRepository.findAll();



        return scheduleList.stream()
                .map(ScheduleAllDetailsResponse::from).toList();
    }

    /**
     * 고유 번호에 해당하는 일정 조회
     *
     * @param scheduleId 일정 고유 번호
     * @return 조회한 일정의 데이터
     */
    @Transactional(readOnly = true)
    public ScheduleAllDetailsResponse findOneSchedule(Long scheduleId) {
        Schedule schedule = validateSchedule(scheduleId);

        return ScheduleAllDetailsResponse.from(schedule);
    }

    /**
     * 고유 번호에 해당하는 일정 수정
     *
     * @param userId     클라이언트의 유저 고유 번호
     * @param request    수정 요청 데이터
     * @param scheduleId 일정 고유 번호
     * @return 수정 완료된 일정 데이터
     */
    @Transactional
    public ScheduleAllDetailsResponse updateSchedule(Long userId, UpdateScheduleRequest request, Long scheduleId) {
        Schedule schedule = validateSchedule(scheduleId);
        validateOwner(schedule, userId);
        schedule.updateSchedule(request.title(), request.content());

        return ScheduleAllDetailsResponse.from(schedule);
    }

    /**
     * 고유 번호에 해당하는 일정 삭제
     *
     * @param scheduleId 일정 고유 번호
     * @param userId     클라이언트의 유저 고유 번호
     */
    @Transactional
    public void deleteSchedule(Long scheduleId, Long userId) {
        Schedule schedule = validateSchedule(scheduleId);
        validateOwner(schedule, userId);

        scheduleRepository.deleteById(scheduleId);
    }
}
