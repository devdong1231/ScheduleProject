package com.scheduleproject.service;

import com.scheduleproject.dto.*;
import com.scheduleproject.entity.Schedule;
import com.scheduleproject.repository.CommentRepository;
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
    private final CommentRepository commentRepository;
    // 400 - IllegalArgumentException
    // 404 - IllegalStateException

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        // 400 - Bad Request 처리
        if (request.getTitle() == null || request.getAuthor() == null || request.getContent() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("필수 입력값이 입력되지 않았습니다!");
        }
        if(request.getTitle().length()>30){
            throw new IllegalArgumentException("제목은 30자 내로 작성해주세요!");
        }
        if(request.getContent().length()>200){
            throw new IllegalArgumentException("내용은 200자 내로 작성해주세요!");
        }

        Schedule schedule = new Schedule(request.getTitle(), request.getAuthor(), request.getContent(), request.getPassword());
        scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt());
    }

    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleId) {
        // 404 - Not Found 처리
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정을 찾을 수 없습니다!")
        );
        List<CommentResponse> commentResponses = commentRepository.findBySchedule(schedule).stream().
                map(comment -> new CommentResponse(
                        comment.getCommentId(),
                        comment.getComment(),
                        comment.getAuthor(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt())
                ).toList();


        return new GetOneScheduleResponse(
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                commentResponses);
    }

    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> getAll(String author) {
        List<Schedule> schedules;
        // 작성자 입력이 있는 지 확인
        if (author == null || author.isBlank()) {
            schedules = scheduleRepository.findAllByOrderByUpdatedAtDesc();
        } else {
            schedules = scheduleRepository.findByAuthorOrderByUpdatedAtDesc(author);
        }
        List<GetAllScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetAllScheduleResponse dto = new GetAllScheduleResponse(
                    schedule.getScheduleId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                // 404 - Not Found 처리
                () -> new IllegalStateException("일정을 찾을 수 없습니다!")
        );

        // 400 - Bad Request 처리
        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다!");
        } else
            schedule.update(
                    request.getTitle(),
                    request.getAuthor());

        return new UpdateScheduleResponse(
                schedule.getScheduleId(),
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
                // 404 - Not Found 처리
                () -> new IllegalStateException("일정을 찾을 수 없습니다!")
        );
        // 400 - Bad Request 처리
        if (!request.getPassword().equals(schedule.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다!");
        }
        commentRepository.deleteBySchedule(schedule);
        scheduleRepository.deleteById(scheduleId);

    }
}
