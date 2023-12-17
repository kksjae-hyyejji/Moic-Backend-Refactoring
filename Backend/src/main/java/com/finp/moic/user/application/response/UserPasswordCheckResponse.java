package com.finp.moic.user.application.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserPasswordCheckResponse {
    private boolean isValid;

    public UserPasswordCheckResponse(){

    }

    @Builder
    public UserPasswordCheckResponse(boolean isValid){
        this.isValid = isValid;
    }
}
