package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPasswordCheckRequest {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String password;

    public UserPasswordCheckRequest(){

    }

    @Builder
    public UserPasswordCheckRequest(@NotNull String password){
        this.password = password;
    }
}
