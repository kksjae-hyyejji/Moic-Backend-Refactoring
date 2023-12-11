package com.finp.moic.auth.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthRefreshResponseDTO {

    private String token;


    public AuthRefreshResponseDTO(){

    }

    @Builder
    public AuthRefreshResponseDTO(String token){
        this.token = token;
    }
}
