package com.scheduleproject.service;


import com.scheduleproject.dto.CreateCommentRequest;
import com.scheduleproject.dto.CreateCommentResponse;
import com.scheduleproject.dto.CreateScheduleRequest;
import com.scheduleproject.entity.Comment;
import com.scheduleproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {
        Comment comment = new Comment(request.getComments(), request.getAuthor(), request.getPassword());
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
