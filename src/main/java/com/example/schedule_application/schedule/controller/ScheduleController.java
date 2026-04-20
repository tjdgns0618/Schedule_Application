package com.example.schedule_application.schedule.controller;

import com.example.schedule_application.schedule.dto.*;
import com.example.schedule_application.schedule.service.ScheduleService;
import com.example.schedule_application.user.dto.SessionUser;
import com.example.schedule_application.user.exception.LoginRequiredException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 로그인 상태인지 검사
     * @param sessionUser 클라이언트의 쿠키에서 가져온 Session
     */
    private void validateSessionUser(SessionUser sessionUser) {
        if(sessionUser == null) {
            throw new LoginRequiredException();
        }
    }

    /**
     * 클라이언트의 세션 검사후 일정 생성
     * @param sessionUser 클라이언트의 쿠키에서 가져온 Session
     * @param request     일정 생성 요청 정보
     * @return 생성된 일정 정보
     */
    @PostMapping
    public ResponseEntity<ScheduleAllDetailsResponse> saveSchedule(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @Valid @RequestBody CreateScheduleRequest request
    ) {
        validateSessionUser(sessionUser);
        ScheduleAllDetailsResponse createResponse = scheduleService.saveSchedule(sessionUser.id(), request);

        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    /**
     * 존재하는 모든 리스트를 조회
     * @return OK응답과 함께 모든 일정 데이터들
     */
    @GetMapping
    public ResponseEntity<List<ScheduleAllDetailsResponse>> getAllSchedule() {
        List<ScheduleAllDetailsResponse> getScheduleResponseList = scheduleService.findAllSchedule();

        return new ResponseEntity<>(getScheduleResponseList, HttpStatus.OK);
    }

    /**
     * 고유 번호에 해당하는 일정을 조회
     * @param scheduleId 조회할 일정 고유 번호
     * @return OK응답과 함께 조회한 일정 데이터
     */
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllDetailsResponse> getOneSchedule(@PathVariable Long scheduleId) {
        ScheduleAllDetailsResponse getScheduleResponse = scheduleService.findOneSchedule(scheduleId);

        return new ResponseEntity<>(getScheduleResponse, HttpStatus.OK);
    }

    /**
     * 고유 번호에 해당하는 일정을 수정
     * @param sessionUser 클라이언트의 쿠키에서 가져온 Session
     * @param request     수정할 데이터
     * @param scheduleId  수정할 일정 고유 번호
     * @return OK응답과 수정 완료한 일정 데이터
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllDetailsResponse> updateSchedule(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @Valid @RequestBody UpdateScheduleRequest request,
            @PathVariable Long scheduleId
    ) {
        validateSessionUser(sessionUser);
        ScheduleAllDetailsResponse updateScheduleResponse = scheduleService.updateSchedule(sessionUser.id(), request, scheduleId);

        return new ResponseEntity<>(updateScheduleResponse, HttpStatus.OK);
    }

    /**
     * 고유 번호에 해당하는 일정을 삭제
     * @param sessionUser 클라이언트의 쿠키에서 가져온 Session
     * @param scheduleId  삭제할 일정 고유 번호
     * @return NO_CONTENT 응답
     */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @PathVariable Long scheduleId
    ) {
        validateSessionUser(sessionUser);
        scheduleService.deleteSchedule(scheduleId, sessionUser.id());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
