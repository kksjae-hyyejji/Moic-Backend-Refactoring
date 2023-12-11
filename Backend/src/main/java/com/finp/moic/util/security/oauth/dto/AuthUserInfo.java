package com.finp.moic.util.security.oauth.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AuthUserInfo {

    private String id;
    private String email;
    private List<String> roles;

    public AuthUserInfo(){

    }

    public AuthUserInfo(String id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }
}