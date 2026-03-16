package com.springAi.ResumeBuilder.controller;

import com.springAi.ResumeBuilder.entity.ResumeHistory;
import com.springAi.ResumeBuilder.entity.User;
import com.springAi.ResumeBuilder.repository.ResumeHistoryRepository;
import com.springAi.ResumeBuilder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class HistoryController {

    private final ResumeHistoryRepository historyRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<ResumeHistory> getHistory() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return historyRepository.findByUserOrderByCreatedAtDesc(user);
    }
}