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
                () -> new IllegalStateException("해당 일정을 찾을 수 없습니다!")
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
        // 작성자 입력이 있는 지 확인
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

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정을 찾을 수 없습니다!")
        );

        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다!");
        } else
            schedule.update(
                    request.getTitle(),
                    request.getAuthor(),
                    request.getPassword());

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );
    }

    @Transactional
    public void delete(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("일정을 찾을 수 없습니다!")
        );
        // 비밀번호가 일치 하지 않는 경우
        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다!");
        } else {
            scheduleRepository.deleteById(scheduleId);
        }
    }
}
