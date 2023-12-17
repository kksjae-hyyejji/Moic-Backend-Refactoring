package com.finp.moic.user.application.response;

import lombok.*;

@Getter
@ToString
public class UserLoginResponse {

    private String name;

    private String accessToken;


    public UserLoginResponse() {
    }

    @Builder
    public UserLoginResponse(String name, String accessToken) {
        this.name = name;
        this.accessToken = accessToken;
    }
}
