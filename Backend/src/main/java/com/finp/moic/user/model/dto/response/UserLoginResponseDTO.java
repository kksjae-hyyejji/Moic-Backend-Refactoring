package com.finp.moic.user.model.dto.response;

import lombok.*;

@Getter
@ToString
public class UserLoginResponseDTO {

    private String name;

    private String accessToken;


    public UserLoginResponseDTO() {
    }

    @Builder
    public UserLoginResponseDTO(String name, String accessToken) {
        this.name = name;
        this.accessToken = accessToken;
    }
}
