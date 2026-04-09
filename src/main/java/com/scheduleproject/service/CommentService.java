package com.scheduleproject.service;


import com.scheduleproject.dto.CreateCommentRequest;
import com.scheduleproject.dto.CreateCommentResponse;
import com.scheduleproject.dto.CreateScheduleRequest;
import com.scheduleproject.entity.Comment;
import com.scheduleproject.entity.Schedule;
import com.scheduleproject.repository.CommentRepository;
import com.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateCommentResponse save(Long scheduleId, CreateCommentRequest request) {
        // 일정 id가 존재하지 않는 경우
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다.")
        );
        // comment가 10개 초과인 경우
        long count = commentRepository.count();
        if(count>10){
            throw new IllegalArgumentException("댓글은 최대 10개까지 작성할 수 있습니다.");
        }

        Comment comment = new Comment(request.getComments(), request.getAuthor(), request.getPassword(), schedule);

        commentRepository.save(comment);

        return new CreateCommentResponse(
                comment.getCommentId(),
                comment.getAuthor(),
                comment.getPassword(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

}
