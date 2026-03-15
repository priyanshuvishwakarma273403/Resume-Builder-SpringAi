package com.example.Spring_Ai_practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;


    public AiService( ChatClient.Builder chatClientBuilder) {
        this.chatClient =  chatClientBuilder.build();
    }


    public String askGroq(String userMessage){
        return chatClient.prompt()
                .system("""
                         You are a helpful AI assistant.
                                                Give clear, simple, and accurate answers.
                        """)
                .user(userMessage)
                .call()
                .content();
    }

}
