package com.finp.moic.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserIdCheckRequestDTO {

    @NotNull
    @NotBlank
    private String id;

    public UserIdCheckRequestDTO(){

    }

    @Builder
    public UserIdCheckRequestDTO(@NotNull String id){
        this.id = id;
    }
}
