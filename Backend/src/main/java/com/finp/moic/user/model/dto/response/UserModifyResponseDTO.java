package com.finp.moic.user.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserModifyResponseDTO {
    private boolean success;

    public UserModifyResponseDTO(){

    }

    @Builder
    public UserModifyResponseDTO(boolean success){
        this.success = success;
    }
}
