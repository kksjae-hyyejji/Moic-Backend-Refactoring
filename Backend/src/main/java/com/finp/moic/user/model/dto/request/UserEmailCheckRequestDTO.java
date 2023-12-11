package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEmailCheckRequestDTO {

    @NotNull
    @NotBlank
    private String email;

    public UserEmailCheckRequestDTO(){

    }

    @Builder
    public UserEmailCheckRequestDTO(@NotNull String email){
        this.email = email;
    }
}
