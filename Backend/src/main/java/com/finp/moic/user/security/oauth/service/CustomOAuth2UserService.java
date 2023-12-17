package com.finp.moic.user.security.oauth.service;

import com.finp.moic.user.application.port.in.UserUseCase;
import com.finp.moic.user.security.oauth.dto.AuthUserInfo;
import com.finp.moic.user.security.oauth.dto.CustomOAuth2User;
import com.finp.moic.user.security.oauth.dto.OAuthUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserUseCase userUseCase;

    public CustomOAuth2UserService(UserUseCase userUseCase){
        this.userUseCase = userUseCase;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        String providerName = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        OAuthUserInfo oAuthUserInfo = OAuthProvider.getOAuthProviderByName(providerName).toUserInfo(oAuth2User);
        AuthUserInfo user = userUseCase.getOrRegisterUser(oAuthUserInfo);

        return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }
}