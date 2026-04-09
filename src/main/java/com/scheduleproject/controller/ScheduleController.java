package com.scheduleproject.controller;

import com.scheduleproject.dto.CreateScheduleRequest;
import com.scheduleproject.dto.CreateScheduleResponse;
import com.scheduleproject.dto.GetOneScheduleResponse;
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
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request){
        CreateScheduleResponse result = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable Long scheduleId){
        GetOneScheduleResponse result = scheduleService.getOne(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("schedules/{author}")
    public ResponseEntity<List<GetOneScheduleResponse>> getAllSchedule(@PathVariable String author){
        List<GetOneScheduleResponse> result = scheduleService.getAll(author);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
