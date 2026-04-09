package com.scheduleproject.controller;

import com.scheduleproject.dto.CreateCommentRequest;
import com.scheduleproject.dto.CreateCommentResponse;
import com.scheduleproject.dto.CreateScheduleResponse;
import com.scheduleproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest request) {
        CreateCommentResponse result = commentService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
