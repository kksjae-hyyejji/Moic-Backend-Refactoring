package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCertificationRequest {

    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String certification;

    public UserCertificationRequest(){

    }
    @Builder
    public UserCertificationRequest(@NotNull String id, @NotNull String certification){
        this.id = id;
        this.certification = certification;
    }
}
