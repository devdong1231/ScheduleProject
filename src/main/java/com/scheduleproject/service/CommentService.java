package com.scheduleproject.service;


import com.scheduleproject.dto.*;
import com.scheduleproject.entity.Comment;
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
        if (count > 10) {
            throw new IllegalArgumentException("댓글은 최대 10개까지 작성할 수 있습니다.");
        }

        Comment comment = new Comment(request.getComment(), request.getAuthor(), request.getPassword(), schedule);

        commentRepository.save(comment);

        return new CreateCommentResponse(
                comment.getCommentId(),
                comment.getAuthor(),
                comment.getPassword(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Transactional
    public List<GetOneCommentResponse> getAll(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다.")
        );
        List<Comment> comments = commentRepository.findAll();
        List<GetOneCommentResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            GetOneCommentResponse dto = new GetOneCommentResponse(
                    comment.getCommentId(),
                    comment.getComments(),
                    comment.getAuthor(),
                    comment.getCreatedAt(),
                    comment.getUpdatedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    public UpdateCommentResponse updateComment(Long scheduleId, long commentId, UpdateCommentRequest request) {
        // 일정 id가 존재하지 않는 경우
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.")
        );
        if (!comment.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        comment.update(request.getComment(), request.getAuthor());

        return new UpdateCommentResponse(
                comment.getCommentId(),
                comment.getComments(),
                comment.getAuthor(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteComment(long scheduleId, long commentId, DeleteCommentRequest request) {
        // 일정 id가 존재하지 않는 경우
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("해당 일정을 찾을 수 없습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글을 찾을 수 없습니다.")
        );
        commentRepository.delete(comment);

    }


}
