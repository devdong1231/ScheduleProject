package com.scheduleproject.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    public final Long commentId;
    public final String comment;
    public final String author;
    public final LocalDateTime createdAt;
    public final LocalDateTime updatedAt;

    public CreateCommentResponse(Long commentId, String comment, String author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.commentId = commentId;
        this.comment = comment;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
