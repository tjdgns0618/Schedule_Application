package com.example.schedule_application.schedule.controller;

import com.example.schedule_application.schedule.dto.*;
import com.example.schedule_application.schedule.service.ScheduleService;
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
    public ResponseEntity<CreateScheduleResponse> saveSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse createResponse = scheduleService.saveSchedule(request);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetSchedulesResponse>> getAllSchedule() {
        List<GetSchedulesResponse> getScheduleResponseList = scheduleService.findAll();
        return new ResponseEntity<>(getScheduleResponseList, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<GetSchedulesResponse> getOneSchedule(@PathVariable Long scheduleId) {
        GetSchedulesResponse getScheduleResponse = scheduleService.findOne(scheduleId);
        return new ResponseEntity<>(getScheduleResponse, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @RequestBody UpdateScheduleRequest request,
            @PathVariable Long scheduleId
    ) {
        UpdateScheduleResponse updateScheduleResponse = scheduleService.updateSchedule(request, scheduleId);
        return new ResponseEntity<>(updateScheduleResponse, HttpStatus.OK);
    }

}
