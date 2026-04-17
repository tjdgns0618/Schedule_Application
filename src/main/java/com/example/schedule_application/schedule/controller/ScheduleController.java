package com.example.schedule_application.schedule.controller;

import com.example.schedule_application.schedule.dto.CreateScheduleRequest;
import com.example.schedule_application.schedule.dto.CreateScheduleResponse;
import com.example.schedule_application.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<CreateScheduleResponse> saveSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse response = scheduleService.saveSchedule(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



}
