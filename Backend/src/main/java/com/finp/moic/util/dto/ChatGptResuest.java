package com.finp.moic.util.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ChatGptResuest {

    private String model;

    private boolean stream;

    private int max_tokens;

    List<ChatGptMessage> messages;

    public ChatGptResuest(String model, boolean stream, int max_tokens, List<ChatGptMessage> messages) {
        this.model = model;
        this.stream = stream;
        this.max_tokens = max_tokens;
        this.messages = messages;
    }
}
