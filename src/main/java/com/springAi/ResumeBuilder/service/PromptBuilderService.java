package com.springAi.ResumeBuilder.service;

import com.springAi.ResumeBuilder.dto.ResumeRequest;
import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {

    public String buildMasterPrompt(ResumeRequest request) {
        return """
                You are an expert career assistant and resume writer.

                Generate the following sections in clean plain text:
                1. Professional Summary
                2. LinkedIn Headline
                3. LinkedIn About
                4. Resume Bullet Points
                5. Project Bullet Points
                6. Recruiter Outreach Message
                7. ATS Friendly Markdown Resume

                Rules:
                - Make the content strong, professional, and achievement-oriented.
                - Keep language natural and recruiter-friendly.
                - Do not use markdown code fences.
                - Separate every section using this exact format:

                ### Professional Summary
                ...
                ### LinkedIn Headline
                ...
                ### LinkedIn About
                ...
                ### Resume Bullet Points
                ...
                ### Project Bullet Points
                ...
                ### Recruiter Outreach Message
                ...
                ### ATS Friendly Markdown Resume
                ...

                Candidate Details:
                Full Name: %s
                Target Role: %s
                Skills: %s
                Experience: %s
                Projects: %s
                Education: %s
                Achievements: %s
                Certifications: %s
                """.formatted(
                request.getFullName(),
                request.getTargetRole(),
                request.getSkills(),
                request.getExperience(),
                request.getProjects(),
                request.getEducation(),
                valueOrDefault(request.getAchievements()),
                valueOrDefault(request.getCertifications())
        );
    }

    private String valueOrDefault(String value) {
        return value == null || value.isBlank() ? "N/A" : value;
    }
}