package com.finp.moic.giftCard.application.request;

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
