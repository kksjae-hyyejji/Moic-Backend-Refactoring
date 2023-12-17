package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserFindPasswordRequest {

    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String email;

    public UserFindPasswordRequest(){

    }
    @Builder
    public UserFindPasswordRequest(@NotNull String id, @NotNull String name, @NotNull String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
