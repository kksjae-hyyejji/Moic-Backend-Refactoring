package com.finp.moic.util.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.finp.moic.util.dto.ChatGptMessage;
import com.finp.moic.util.dto.ChatGptResuest;
import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGptService {

    @Value("${CHAT_APIURL}")
    private String apiUrl;

    @Value("${CHAT_APIKEY}")
    private String apiKey;

    @Value("${CHAT_MODEL}")
    private String model;
    private final WebClient webClient;

    @Autowired
    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String response(List<String> texts){

        ChatGptResuest chatGptResuest =  makeChatGptRequest(texts);

        String response= postChatGptRequest(chatGptResuest);

        String content = getContent(response);

        if (content.equals("정상적인 이미지가 아닙니다.")) {

            throw new BusinessException(ExceptionEnum.GIFTCATD_INVALID);
        }

        return content;
    }

    public String getContent(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        String content;
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            content = jsonNode.get("choices").get(0).get("message").get("content").asText();

        } catch(Exception e) {
            throw new BusinessException(ExceptionEnum.GIFTCATD_INVALID);
        }

        return content;
    }

    public String postChatGptRequest(ChatGptResuest chatGptResuest) {
       return webClient.post()
                .uri("")
                .bodyValue(chatGptResuest)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public ChatGptResuest makeChatGptRequest(List<String> texts) {

        List<ChatGptMessage> list=new ArrayList<>(); // 성재 : ChatGpt api의 Request 형식에서 list를 요구하므로 불가피함.
        list.add(
                ChatGptMessage.builder()
                        .role("user")
                        .content("상호명, 마지막 날짜인 유효기간으로 정제해줘. 예를 들어, '상호명:BBQ', '유효기간:2023-09-27' 처럼 한줄씩 정리해줘. 만약 유추되는 데이터가 없다면, \"정상적인 이미지가 아닙니다\". 를 대답해줘.")
                        .build()
        );

        ChatGptResuest chatGptResuest = ChatGptResuest.builder()
                .model(model)
                .max_tokens(300)
                .stream(false)
                .messages(list)
                .build();

        StringBuffer sb = new StringBuffer();
        sb.append(chatGptResuest.getMessages().get(0).getContent().toString()).append("\n");

        for (String text : texts) {
            sb.append(text).append("\n");
        }
        chatGptResuest.getMessages().get(0).setContent(sb.toString());
        sb.setLength(0);

        return chatGptResuest;
    }

}
