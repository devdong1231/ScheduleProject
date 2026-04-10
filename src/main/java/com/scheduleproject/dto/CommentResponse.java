package com.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final Long commentId;
    private final String comment;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CommentResponse(Long commentId, String comment, String author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.comment = comment;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
