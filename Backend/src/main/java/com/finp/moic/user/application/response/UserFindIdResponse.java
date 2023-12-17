package com.finp.moic.user.application.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserFindIdResponse {
    private String id;

    public UserFindIdResponse(){

    }

    @Builder
    public UserFindIdResponse(String id){
        this.id = id;
    }
}
