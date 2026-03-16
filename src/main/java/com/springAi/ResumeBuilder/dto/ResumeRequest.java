package com.springAi.ResumeBuilder.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResumeRequest {

    @NotBlank
    private String provider; // groq / openrouter

    @NotBlank
    private String fullName;

    @NotBlank
    private String targetRole;

    @NotBlank
    private String skills;

    @NotBlank
    private String experience;

    @NotBlank
    private String projects;

    @NotBlank
    private String education;

    private String achievements;
    private String certifications;

}
