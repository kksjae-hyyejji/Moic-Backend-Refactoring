package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
public class UserLoginRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String password;

    public UserLoginRequest() {
    }

    @Builder
    public UserLoginRequest(@NotNull String id, @NotNull String password) {
        this.id = id;
        this.password = password;
    }

}
