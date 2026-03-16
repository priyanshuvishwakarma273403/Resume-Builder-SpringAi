package com.springAi.ResumeBuilder.service;

import com.springAi.ResumeBuilder.dto.ResumeRequest;
import com.springAi.ResumeBuilder.dto.ResumeResponse;
import com.springAi.ResumeBuilder.dto.SectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeAiService {

    @Qualifier("groqChatClient")
    private final ChatClient groqChatClient;

    @Qualifier("openRouterChatClient")
    private final ChatClient openRouterChatClient;

    private final PromptBuilderService promptBuilderService;

    public ResumeResponse generateResumeContent(ResumeRequest request) {
        String prompt = promptBuilderService.buildMasterPrompt(request);
        ChatClient selectedClient = getClient(request.getProvider());

        String response = selectedClient.prompt()
                .system("You are a world-class resume and LinkedIn profile writer.")
                .user(prompt)
                .call()
                .content();

        ResumeResponse resumeResponse = new ResumeResponse();
        resumeResponse.setProvider(request.getProvider());
        resumeResponse.setSections(parseSections(response));
        return resumeResponse;
    }

    private ChatClient getClient(String provider) {
        return switch (provider.toLowerCase()) {
            case "groq" -> groqChatClient;
            case "openrouter" -> openRouterChatClient;
            default -> throw new IllegalArgumentException("Invalid provider. Use 'groq' or 'openrouter'.");
        };
    }

    private List<SectionResponse> parseSections(String text) {
        String[] markers = {
                "### Professional Summary",
                "### LinkedIn Headline",
                "### LinkedIn About",
                "### Resume Bullet Points",
                "### Project Bullet Points",
                "### Recruiter Outreach Message",
                "### ATS Friendly Markdown Resume"
        };

        List<SectionResponse> sections = new ArrayList<>();

        for (int i = 0; i < markers.length; i++) {
            int start = text.indexOf(markers[i]);
            if (start == -1) continue;

            int contentStart = start + markers[i].length();
            int end = (i + 1 < markers.length) ? text.indexOf(markers[i + 1]) : text.length();
            if (end == -1) end = text.length();

            String title = markers[i].replace("### ", "").trim();
            String content = text.substring(contentStart, end).trim();

            sections.add(new SectionResponse(title, content));
        }

        if (sections.isEmpty()) {
            sections.add(new SectionResponse("Generated Content", text));
        }

        return sections;
    }
}