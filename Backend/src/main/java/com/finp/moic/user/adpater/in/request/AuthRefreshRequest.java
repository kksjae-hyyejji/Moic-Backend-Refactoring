package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AuthRefreshRequest {

    @NotNull
    @NotBlank
    private String accessToken;

    public AuthRefreshRequest(){

    }

    public AuthRefreshRequest(String accessToken){
        this.accessToken = accessToken;
    }

}
