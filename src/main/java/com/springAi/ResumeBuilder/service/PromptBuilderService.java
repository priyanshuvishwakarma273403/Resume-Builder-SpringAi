package com.springAi.ResumeBuilder.service;

import com.springAi.ResumeBuilder.dto.ResumeRequest;
import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {

    public String buildMasterPrompt(ResumeRequest request) {
        String templateInstruction = switchTemplate(request.getSelectedTemplate());

        return """
                You are an expert resume writer.

                Use this style template:
                %s

                Generate:
                1. Professional Summary
                2. LinkedIn Headline
                3. LinkedIn About
                4. Resume Bullet Points
                5. Project Bullet Points
                6. Recruiter Outreach Message
                7. ATS Friendly Markdown Resume

                If job description is provided, tailor the output strongly according to it.

                Output section format:
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

                Candidate:
                Full Name: %s
                Target Role: %s
                Skills: %s
                Experience: %s
                Projects: %s
                Education: %s
                Achievements: %s
                Certifications: %s

                Job Description:
                %s
                """.formatted(
                templateInstruction,
                safe(request.getFullName()),
                safe(request.getTargetRole()),
                safe(request.getSkills()),
                safe(request.getExperience()),
                safe(request.getProjects()),
                safe(request.getEducation()),
                safe(request.getAchievements()),
                safe(request.getCertifications()),
                safe(request.getJobDescription())
        );
    }

    private String switchTemplate(String template) {
        if (template == null) return "Modern professional tone with concise achievement bullets.";
        return switch (template.toLowerCase()) {
            case "minimal" -> "Minimal, crisp, highly ATS-friendly, no flashy wording.";
            case "executive" -> "Executive tone, leadership-oriented, strategic impact language.";
            case "developer" -> "Technical, impact-focused, modern software engineer branding.";
            default -> "Modern professional tone with concise achievement bullets.";
        };
    }

    private String safe(String value) {
        return value == null || value.isBlank() ? "N/A" : value;
    }
}