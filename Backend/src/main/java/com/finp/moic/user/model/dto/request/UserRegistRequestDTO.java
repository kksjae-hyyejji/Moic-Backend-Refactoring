package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@ToString
public class UserRegistRequestDTO {

    @NotNull
    @Pattern(regexp = "^[a-z0-9]{6,12}$")
    private String id;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String password;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,16}$")
    private String passwordCheck;

    @NotNull
    @NotBlank
    @NotEmpty
    private String name;

    @NotNull
    @Email
    private String email;

    private String gender;

    private int yearOfBirth;

    public UserRegistRequestDTO() {
    }

    @Builder
    public UserRegistRequestDTO(@NotNull String id, @NotNull String password, @NotNull String passwordCheck,
                                @NotNull String name, @NotNull String email, String gender,
                                int yearOfBirth) {
        this.id = id;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
    }
}
