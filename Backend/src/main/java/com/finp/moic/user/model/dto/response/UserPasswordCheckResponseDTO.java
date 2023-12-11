package com.finp.moic.user.model.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPasswordCheckResponseDTO {
    private boolean isValid;

    public UserPasswordCheckResponseDTO(){

    }

    @Builder
    public UserPasswordCheckResponseDTO(boolean isValid){
        this.isValid = isValid;
    }
}
