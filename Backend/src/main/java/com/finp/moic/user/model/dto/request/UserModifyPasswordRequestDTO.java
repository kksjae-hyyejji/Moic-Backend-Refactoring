package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserModifyPasswordRequestDTO {


    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String newPassword;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String newPasswordCheck;

    public UserModifyPasswordRequestDTO() {
    }

    @Builder
    public UserModifyPasswordRequestDTO(@NotNull String newPassword, @NotNull String newPasswordCheck) {
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
    }
}
