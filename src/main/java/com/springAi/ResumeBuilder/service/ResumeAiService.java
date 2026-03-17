package com.springAi.ResumeBuilder.service;

import com.springAi.ResumeBuilder.dto.ResumeRequest;
import com.springAi.ResumeBuilder.dto.ResumeResponse;
import com.springAi.ResumeBuilder.dto.SectionResponse;
import com.springAi.ResumeBuilder.entity.ResumeHistory;
import com.springAi.ResumeBuilder.entity.User;
import com.springAi.ResumeBuilder.repository.ResumeHistoryRepository;
import com.springAi.ResumeBuilder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final ResumeHistoryRepository historyRepository;
    private final UserRepository userRepository;

    public ResumeResponse generateResumeContent(ResumeRequest request) {
        String prompt = promptBuilderService.buildMasterPrompt(request);

        ChatClient client = getClient(request.getProvider());

        String response = client.prompt()
                .system("You are a world-class resume and LinkedIn profile writer.")
                .user(prompt)
                .call()
                .content();

        if (response == null || response.isBlank()) {
            throw new RuntimeException("Empty response received from AI provider");
        }

//        saveHistory(request, response);

        ResumeResponse result = new ResumeResponse();
        result.setProvider(request.getProvider());
        result.setSections(parseSections(response));
        return result;
    }

    public String generateMarkdownOnly(ResumeRequest request) {
        String prompt = promptBuilderService.buildMasterPrompt(request);
        ChatClient client = getClient(request.getProvider());
        return client.prompt()
                .system("Return polished ATS-friendly markdown resume only.")
                .user(prompt)
                .call()
                .content();
    }

    private ChatClient getClient(String provider) {
        return "openrouter".equalsIgnoreCase(provider) ? openRouterChatClient : groqChatClient;
    }

    private void saveHistory(ResumeRequest request, String response) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getName())) {
            return;
        }

        userRepository.findByEmail(authentication.getName()).ifPresent(user -> {
            ResumeHistory history = ResumeHistory.builder()
                    .provider(request.getProvider())
                    .fullName(request.getFullName())
                    .targetRole(request.getTargetRole())
                    .requestPayload(request.toString())
                    .generatedContent(response)
                    .selectedTemplate(request.getSelectedTemplate())
                    .createdAt(LocalDateTime.now())
                    .user(user)
                    .build();

            historyRepository.save(history);
        });
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
            int end = i + 1 < markers.length ? text.indexOf(markers[i + 1]) : text.length();
            if (end == -1) end = text.length();

            sections.add(new SectionResponse(
                    markers[i].replace("### ", ""),
                    text.substring(contentStart, end).trim()
            ));
        }

        if (sections.isEmpty()) {
            sections.add(new SectionResponse("Generated Content", text));
        }

        return sections;
    }
}