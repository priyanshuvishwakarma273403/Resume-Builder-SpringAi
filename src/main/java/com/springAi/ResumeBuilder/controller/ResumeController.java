package com.springAi.ResumeBuilder.controller;

import com.springAi.ResumeBuilder.dto.*;
import com.springAi.ResumeBuilder.service.AtsAnalyzerService;
import com.springAi.ResumeBuilder.service.CompareAiService;
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
    private final CompareAiService compareAiService;
    private final AtsAnalyzerService atsAnalyzerService;

    @PostMapping("/generate")
    public ResumeResponse generate(@RequestBody ResumeRequest request) {
        return resumeAiService.generateResumeContent(request);
    }

    @PostMapping("/compare")
    public CompareResponse compare(@RequestBody ResumeRequest request) {
        return compareAiService.compare(request);
    }

    @PostMapping("/markdown")
    public String markdown(@RequestBody ResumeRequest request) {
        return resumeAiService.generateMarkdownOnly(request);
    }

    @PostMapping("/ats-score")
    public AtsResponse atsScore(@RequestBody AtsRequest request) {
        return atsAnalyzerService.analyze(request);
    }
}
