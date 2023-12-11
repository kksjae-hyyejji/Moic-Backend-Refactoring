package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserResetPasswordRequestDTO {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String password;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String passwordCheck;

    @NotNull
    @NotBlank
    private String certification;

    public UserResetPasswordRequestDTO() {
    }

    @Builder
    public UserResetPasswordRequestDTO(@NotNull String id, @NotNull String password, @NotNull String passwordCheck, @NotNull String certification) {
        this.id = id;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.certification = certification;
    }
}
