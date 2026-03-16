package com.springAi.ResumeBuilder.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String fullName;
    private String email;
    private String password;
}
