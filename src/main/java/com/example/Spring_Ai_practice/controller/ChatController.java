package com.example.Spring_Ai_practice.controller;


import com.example.Spring_Ai_practice.service.AiService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@Validated
public class ChatController {

    private final AiService aiService;

    @Autowired
    public ChatController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping
    public Map<String, String>  chatWithParam(@RequestParam @NotBlank String message){
        String response = aiService.askGroq(message);
        return Map.of(
                "userMessage",message,
                "aiResponse",response
        );
    }

    @PostMapping
    public Map<String, String> chatWithBody(@RequestBody Map<String, String> body){
        String message =  body.get("message");

        if(message == null || message.isEmpty()){
            throw new IllegalArgumentException("message must not be empty");
        }
        String response  = aiService.askGroq(message);

        return Map.of(
                "userMessage", message,
                "aiResponse", response
        );
    }

}
