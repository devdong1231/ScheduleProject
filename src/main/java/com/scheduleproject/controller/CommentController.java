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
    public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest request, @PathVariable long scheduleId) {
        CreateCommentResponse result;
        try {
            result = commentService.save(scheduleId, request);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("schedules/{scheduleId}/comments")
    public ResponseEntity<?> getAllComment(@PathVariable Long scheduleId) {
        List<GetOneCommentResponse> results;
        try {
            results = commentService.getAll(scheduleId);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @PostMapping("schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody UpdateCommentRequest request) {
        UpdateCommentResponse result;
        try {
            result = commentService.updateComment(scheduleId, commentId, request);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long scheduleId, @PathVariable Long commentId, @RequestBody DeleteCommentRequest request) {
        try {
            commentService.deleteComment(scheduleId, commentId, request);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
