package com.finp.moic.user.application.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthRefreshResponse {

    private String token;


    public AuthRefreshResponse(){

    }

    @Builder
    public AuthRefreshResponse(String token){
        this.token = token;
    }
}
