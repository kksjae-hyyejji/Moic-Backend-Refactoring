package com.finp.moic.user.adpater.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserIdCheckRequest {

    @NotNull
    @NotBlank
    private String id;

    public UserIdCheckRequest(){

    }

    @Builder
    public UserIdCheckRequest(@NotNull String id){
        this.id = id;
    }
}
