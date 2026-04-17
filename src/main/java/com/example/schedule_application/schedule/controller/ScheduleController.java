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
    public ResponseEntity<ScheduleResponse> saveSchedule(@Valid @RequestBody CreateScheduleRequest request) {
        ScheduleResponse createResponse = scheduleService.saveSchedule(request);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllSchedule() {
        List<ScheduleResponse> getScheduleResponseList = scheduleService.findAll();
        return new ResponseEntity<>(getScheduleResponseList, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getOneSchedule(@PathVariable Long scheduleId) {
        ScheduleResponse getScheduleResponse = scheduleService.findOne(scheduleId);
        return new ResponseEntity<>(getScheduleResponse, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @Valid @RequestBody UpdateScheduleRequest request,
            @PathVariable Long scheduleId
    ) {
        ScheduleResponse updateScheduleResponse = scheduleService.updateSchedule(request, scheduleId);
        return new ResponseEntity<>(updateScheduleResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
