package com.finp.moic.util.security.oauth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthUserInfo {

    private String email;
    private String nickname;
    private String provider;
    private String oauthId;

    public OAuthUserInfo(){

    }

    @Builder
    public OAuthUserInfo(String email, String nickname, String provider, String oauthId) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
        this.oauthId = oauthId;
    }
}
