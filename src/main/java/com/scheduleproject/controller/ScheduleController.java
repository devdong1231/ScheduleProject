package com.scheduleproject.controller;

import com.scheduleproject.dto.*;
import com.scheduleproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<?> createSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result;
        try {
            result = scheduleService.save(request);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> getOneSchedule(@PathVariable Long scheduleId) {
        GetOneScheduleResponse result;
        try {
            result = scheduleService.getOne(scheduleId);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetAllScheduleResponse>> getAllSchedule(@RequestParam(required = false) String author) {
        List<GetAllScheduleResponse> result = scheduleService.getAll(author);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long scheduleId, @RequestBody UpdateScheduleRequest request) {
        UpdateScheduleResponse result;
        try {
            result = scheduleService.update(scheduleId, request);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Long scheduleId, @RequestBody DeleteScheduleRequest request) {
        try {
            scheduleService.delete(scheduleId, request);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
