package com.scheduleproject.service;

import com.scheduleproject.dto.*;
import com.scheduleproject.entity.Schedule;
import com.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getAuthor(), request.getContent(), request.getPassword());
        scheduleRepository.save(schedule);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당하는 일정이 없습니다.")
        );

        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll(String author) {
        List<Schedule> schedules;
        if (author == null || author.isBlank()) {
            schedules = scheduleRepository.findAllByOrderByUpdatedAtDesc();
        } else {
            schedules = scheduleRepository.findByAuthorOrderByUpdatedAtDesc(author);
        }
        List<GetOneScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetOneScheduleResponse dto = new GetOneScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt());
            dtos.add(dto);
        }
        return dtos;
    }
}
