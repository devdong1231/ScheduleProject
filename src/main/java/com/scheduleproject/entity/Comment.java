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
    // 댓글 내용, 작성자명, 비밀번호, 작성/수정일, 일정 고유식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false)
    private String comments;
    @Column(length = 100, nullable = false)
    private String author;
    @Column(length = 255, nullable = false)
    private String password;

    public Comment(String comments, String author, String password) {
        this.comments = comments;
        this.author = author;
        this.password = password;
    }

    public void update(String comments, String author) {
        this.comments = comments;
        this.author = author;
    }

}
