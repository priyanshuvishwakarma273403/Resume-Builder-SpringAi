package com.springAi.ResumeBuilder.dto;

import lombok.Data;


@Data
public class ResumeRequest {
    private String provider;
    private String fullName;
    private String targetRole;
    private String skills;
    private String experience;
    private String projects;
    private String education;
    private String achievements;
    private String certifications;
    private String selectedTemplate;
    private String jobDescription;
}