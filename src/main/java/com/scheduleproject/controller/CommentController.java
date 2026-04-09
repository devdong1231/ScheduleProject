package com.scheduleproject.controller;

import com.scheduleproject.dto.*;
import com.scheduleproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest request, @PathVariable long scheduleId) {
        CreateCommentResponse result = commentService.save(scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("schedules/{scheduleId}/comments")
    public ResponseEntity<List<GetOneCommentResponse>> getAllComment(@PathVariable Long scheduleId){
        List<GetOneCommentResponse> results = commentService.getAll(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PostMapping("schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<UpdateCommentResponse> updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody UpdateCommentRequest request){
        UpdateCommentResponse result = commentService.updateComment(scheduleId, commentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


}
