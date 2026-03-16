package com.springAi.ResumeBuilder.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenRouterConfig {

    @Bean
    public ChatClient openRouterChatClient(
            @Value("${app.openrouter.api-key}") String apiKey,
            @Value("${app.openrouter.base-url}") String baseUrl,
            @Value("${app.openrouter.model}") String model
    ){
        OpenAiApi api = OpenAiApi.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                .build();

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model(model)
                .temperature(0.8)
                .build();

        OpenAiChatModel chatModel = new OpenAiChatModel(api, options);
        return ChatClient.builder(chatModel).build();
    }

}
