package com.springAi.ResumeBuilder.repository;

import com.springAi.ResumeBuilder.entity.ResumeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springAi.ResumeBuilder.entity.User;
import java.util.List;

public interface ResumeHistoryRepository extends JpaRepository<ResumeHistory, Long> {
    List<ResumeHistory> findByUserOrderByCreatedAtDesc(User user);
}
