package com.example.schedule_application.schedule.controller;

import com.example.schedule_application.schedule.dto.*;
import com.example.schedule_application.schedule.service.ScheduleService;
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
    public ResponseEntity<ScheduleAllDetailsResponse> saveSchedule(@Valid @RequestBody CreateScheduleRequest request) {
        ScheduleAllDetailsResponse createResponse = scheduleService.saveSchedule(request);
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
            @Valid @RequestBody UpdateScheduleRequest request,
            @PathVariable Long scheduleId
    ) {
        ScheduleAllDetailsResponse updateScheduleResponse = scheduleService.updateSchedule(request, scheduleId);
        return new ResponseEntity<>(updateScheduleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
