package com.vsempandets.aibot.controller;

import com.vsempandets.aibot.ChatRequest;
import com.vsempandets.aibot.service.ChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bot")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> chat(@RequestBody ChatRequest chatRequest) {
        return chatService.chat(chatRequest);
    }

}
