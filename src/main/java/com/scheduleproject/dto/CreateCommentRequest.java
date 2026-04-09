package com.scheduleproject.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String comments;
    private String author;
    private String password;
}
