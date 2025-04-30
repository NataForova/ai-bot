package com.vsempandets.aibot.service;

import com.vsempandets.aibot.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient.Builder chatClientBuilder) {
        ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
        this.chatClient = chatClientBuilder.defaultAdvisors(
                new MessageChatMemoryAdvisor(chatMemory)
        ).build();
    }

    public Flux<String> chat(ChatRequest chatRequest) {
        return chatClient.prompt()
                .advisors( a -> a.param("chat_memory_conversation_id", chatRequest.conversationId()))
                .user(chatRequest.text())
                .stream()
                .content();
    }

}
