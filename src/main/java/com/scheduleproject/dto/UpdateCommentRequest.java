package com.scheduleproject.dto;

import lombok.Getter;

@Getter
public class UpdateCommentRequest {
    private String comment;
    private String author;
    private String password;

}
