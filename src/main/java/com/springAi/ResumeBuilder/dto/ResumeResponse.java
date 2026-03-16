package com.springAi.ResumeBuilder.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResumeResponse {
    private String provider;
    private List<SectionResponse> sections;
}
