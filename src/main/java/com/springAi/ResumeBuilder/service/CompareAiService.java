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

    public CompareResponse compare(ResumeRequest request){
        String prompt = promptBuilderService.buildMasterPrompt(request);

        long groqStart = System.currentTimeMillis();

       String groqResponse = groqChatClient.prompt()
               .system("You are a world-class resume writer.")
               .user(prompt)
               .call()
               .content();
        long groqLatency = System.currentTimeMillis() - groqStart;

        long openStart = System.currentTimeMillis();
        String openResponse = openRouterChatClient.prompt()
                .system("You are a world-class resume writer.")
                .user(prompt)
                .call()
                .content();
        long openLatency = System.currentTimeMillis() - openStart;

        return new CompareResponse(groqResponse, openResponse, groqLatency, openLatency);
    }
}
