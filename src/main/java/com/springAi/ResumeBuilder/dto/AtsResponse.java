package com.springAi.ResumeBuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtsResponse {
    private int score;
    private String strengths;
    private String improvements;
    private String missingKeywords;
}
