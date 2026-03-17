package com.springAi.ResumeBuilder.service;

import com.springAi.ResumeBuilder.dto.CompareResponse;
import com.springAi.ResumeBuilder.dto.ResumeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompareAiService {

    @Qualifier("groqChatClient")
    private final ChatClient groqChatClient;

    @Qualifier("openRouterChatClient")
    private final ChatClient openRouterChatClient;

    private final PromptBuilderService promptBuilderService;

    public CompareResponse compare(ResumeRequest request) {
        String prompt = promptBuilderService.buildMasterPrompt(request);

        String groqResponse;
        long groqLatency;

        long groqStart = System.currentTimeMillis();
        try {
            groqResponse = groqChatClient.prompt()
                    .system("You are a world-class resume writer.")
                    .user(prompt)
                    .call()
                    .content();

            if (groqResponse == null || groqResponse.isBlank()) {
                groqResponse = "Groq returned empty response.";
            }
        } catch (Exception e) {
            groqResponse = "Groq failed: " + e.getMessage();
        }
        groqLatency = System.currentTimeMillis() - groqStart;

        String openResponse;
        long openLatency;

        long openStart = System.currentTimeMillis();
        try {
            openResponse = openRouterChatClient.prompt()
                    .system("You are a world-class resume writer.")
                    .user(prompt)
                    .call()
                    .content();

            if (openResponse == null || openResponse.isBlank()) {
                openResponse = "OpenRouter returned empty response.";
            }
        } catch (Exception e) {
            openResponse = "OpenRouter failed: " + e.getMessage();
        }
        openLatency = System.currentTimeMillis() - openStart;

        return new CompareResponse(groqResponse, openResponse, groqLatency, openLatency);
    }
}