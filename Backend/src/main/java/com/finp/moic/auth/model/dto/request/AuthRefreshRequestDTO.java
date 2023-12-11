package com.finp.moic.auth.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AuthRefreshRequestDTO {

    @NotNull
    @NotBlank
    private String accessToken;

    public AuthRefreshRequestDTO(){

    }

    public AuthRefreshRequestDTO(String accessToken){
        this.accessToken = accessToken;
    }

}
