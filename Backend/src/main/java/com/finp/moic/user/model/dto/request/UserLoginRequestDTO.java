package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
public class UserLoginRequestDTO {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String password;

    public UserLoginRequestDTO() {
    }

    @Builder
    public UserLoginRequestDTO(@NotNull String id, @NotNull String password) {
        this.id = id;
        this.password = password;
    }

}
