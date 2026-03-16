package com.springAi.ResumeBuilder.controller;

import com.springAi.ResumeBuilder.dto.ResumeRequest;
import com.springAi.ResumeBuilder.dto.ResumeResponse;
import com.springAi.ResumeBuilder.service.ResumeAiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ResumeController {

    private final ResumeAiService resumeAiService;

    @PostMapping("/generate")
    public ResumeResponse generate(@Valid @RequestBody ResumeRequest request) {
        return resumeAiService.generateResumeContent(request);
    }
}
