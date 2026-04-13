package com.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(length = 100, nullable = false)
    private String comment;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String password;


    private Long scheduleId;

    public Comment(String comment, String author, String password, Long scheduleId) {
        this.comment = comment;
        this.author = author;
        this.password = password;
        this.scheduleId = scheduleId;
    }

    public void update(String comments, String author) {
        this.comment = comments;
        this.author = author;
    }

}
