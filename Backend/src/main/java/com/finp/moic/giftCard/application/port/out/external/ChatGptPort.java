package com.finp.moic.giftCard.application.port.out.external;

import com.finp.moic.giftCard.application.request.ChatGptResuest;

import java.util.List;

public interface ChatGptPort {

    public String response(List<String> texts);

    public String getContent(String response);

    public String postChatGptRequest(ChatGptResuest chatGptResuest);

    public ChatGptResuest makeChatGptRequest(List<String> texts);
}
