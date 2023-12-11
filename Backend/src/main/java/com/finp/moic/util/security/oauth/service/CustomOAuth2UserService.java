package com.finp.moic.util.security.oauth.service;

import com.finp.moic.user.model.service.UserService;
import com.finp.moic.util.security.oauth.dto.AuthUserInfo;
import com.finp.moic.util.security.oauth.dto.CustomOAuth2User;
import com.finp.moic.util.security.oauth.dto.OAuthUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    public CustomOAuth2UserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        String providerName = oAuth2UserRequest.getClientRegistration().getRegistrationId();

        OAuthUserInfo oAuthUserInfo = OAuthProvider.getOAuthProviderByName(providerName).toUserInfo(oAuth2User);
        AuthUserInfo user = userService.getOrRegisterUser(oAuthUserInfo);

        return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }
}