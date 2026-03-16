package com.springAi.ResumeBuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompareResponse {
    private String groqResponse;
    private String openRouterResponse;
    private long groqLatencyMs;
    private long openRouterLatencyMs;
}
