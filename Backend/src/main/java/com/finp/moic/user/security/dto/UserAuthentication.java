package com.finp.moic.user.security.dto;

import lombok.Getter;

@Getter
public class UserAuthentication {
    private String id;

    public UserAuthentication(String id){
        this.id = id;
    }
}
