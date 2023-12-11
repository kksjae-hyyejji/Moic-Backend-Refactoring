package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserFindIdRequestDTO {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String email;

    public UserFindIdRequestDTO(){

    }

    @Builder
    public UserFindIdRequestDTO(@NotNull String name, @NotNull String email){
        this.name = name;
        this.email = email;
    }
}
