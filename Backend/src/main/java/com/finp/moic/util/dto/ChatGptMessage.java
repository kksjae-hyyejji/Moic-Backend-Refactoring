package com.finp.moic.util.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChatGptMessage {

    String role;
    String content;
}
