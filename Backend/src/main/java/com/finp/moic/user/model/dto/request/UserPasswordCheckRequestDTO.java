package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPasswordCheckRequestDTO {

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String password;

    public UserPasswordCheckRequestDTO(){

    }

    @Builder
    public UserPasswordCheckRequestDTO(@NotNull String password){
        this.password = password;
    }
}
