package com.scheduleproject.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(length = 200, nullable = false)
    private String contents;
    @Column(nullable = false)
    private String password;

    public Schedule(String title, String author, String contents, String password) {
        this.title = title;
        this.author = author;
        this.contents = contents;
        this.password = password;
    }

    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
