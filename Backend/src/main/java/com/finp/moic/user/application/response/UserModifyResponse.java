package com.finp.moic.user.application.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserModifyResponse {
    private boolean success;

    public UserModifyResponse(){

    }

    @Builder
    public UserModifyResponse(boolean success){
        this.success = success;
    }
}
