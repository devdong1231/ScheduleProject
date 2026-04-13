package com.scheduleproject.repository;

import com.scheduleproject.dto.CommentResponse;
import com.scheduleproject.entity.Comment;
import com.scheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByScheduleId(Long scheduleId);

    void deleteByScheduleId(Long scheduleId);

    List<Comment> findByScheduleId(Long scheduleId);
}
