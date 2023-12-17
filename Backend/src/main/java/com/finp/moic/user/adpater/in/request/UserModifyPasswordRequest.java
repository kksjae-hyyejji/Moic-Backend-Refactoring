package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserModifyPasswordRequest {


    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String newPassword;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String newPasswordCheck;

    public UserModifyPasswordRequest() {
    }

    @Builder
    public UserModifyPasswordRequest(@NotNull String newPassword, @NotNull String newPasswordCheck) {
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
    }
}
