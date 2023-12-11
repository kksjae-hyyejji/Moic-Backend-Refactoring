package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCertificationRequestDTO {

    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String certification;

    public UserCertificationRequestDTO(){

    }
    @Builder
    public UserCertificationRequestDTO(@NotNull String id, @NotNull String certification){
        this.id = id;
        this.certification = certification;
    }
}
