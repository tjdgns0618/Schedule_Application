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

    @PostMapping
    public ResponseEntity<ScheduleAllDetailsResponse> saveSchedule(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @Valid @RequestBody CreateScheduleRequest request
    ) {
        validateSessionUser(sessionUser);

        ScheduleAllDetailsResponse createResponse = scheduleService.saveSchedule(sessionUser.getId(), request);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleAllDetailsResponse>> getAllSchedule() {
        List<ScheduleAllDetailsResponse> getScheduleResponseList = scheduleService.findAll();
        return new ResponseEntity<>(getScheduleResponseList, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllDetailsResponse> getOneSchedule(@PathVariable Long scheduleId) {
        ScheduleAllDetailsResponse getScheduleResponse = scheduleService.findOne(scheduleId);
        return new ResponseEntity<>(getScheduleResponse, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleAllDetailsResponse> updateSchedule(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @Valid @RequestBody UpdateScheduleRequest request,
            @PathVariable Long scheduleId
    ) {
        validateSessionUser(sessionUser);
        ScheduleAllDetailsResponse updateScheduleResponse = scheduleService.updateSchedule(sessionUser.getId(), request, scheduleId);
        return new ResponseEntity<>(updateScheduleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @PathVariable Long scheduleId
    ) {
        validateSessionUser(sessionUser);
        scheduleService.deleteSchedule(scheduleId, sessionUser.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validateSessionUser(SessionUser sessionUser) {
        if(sessionUser == null) {
            throw new LoginRequiredException();
        }
    }

}
