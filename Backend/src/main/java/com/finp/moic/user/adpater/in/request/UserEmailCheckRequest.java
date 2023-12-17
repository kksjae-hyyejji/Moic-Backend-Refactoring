package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEmailCheckRequest {

    @NotNull
    @NotBlank
    private String email;

    public UserEmailCheckRequest(){

    }

    @Builder
    public UserEmailCheckRequest(@NotNull String email){
        this.email = email;
    }
}
