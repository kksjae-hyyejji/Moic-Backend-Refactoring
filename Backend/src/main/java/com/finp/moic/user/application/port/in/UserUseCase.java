package com.finp.moic.user.application.port.in;


import com.finp.moic.user.adpater.in.request.*;
import com.finp.moic.user.application.response.UserDetailResponse;
import com.finp.moic.user.application.response.UserFindIdResponse;
import com.finp.moic.user.application.response.UserLoginResponse;
import com.finp.moic.user.security.dto.UserAuthentication;
import com.finp.moic.user.security.oauth.dto.AuthUserInfo;
import com.finp.moic.user.security.oauth.dto.OAuthUserInfo;
import jakarta.servlet.http.HttpServletResponse;

public interface UserUseCase {

    UserLoginResponse login(UserLoginRequest dto, HttpServletResponse httpResponse);
    void regist(UserRegistRequest dto);
    boolean isIdValidate(UserIdCheckRequest dto);
    boolean isEmailValidate(UserEmailCheckRequest dto);
    void isPasswordValidate(String id, UserPasswordCheckRequest dto);
    void logout(UserAuthentication userAuthentication,String refreshToken);
    UserDetailResponse getUserDetail(String id);
    void modifyUser(String id, UserModifyRequest dto);
    void modifyPassword(String id, UserModifyPasswordRequest dto);
    void deleteUser(String id);
    AuthUserInfo getOrRegisterUser(OAuthUserInfo oAuthUserInfo);
    UserFindIdResponse findId(UserFindIdRequest dto);
    void issueNumber(UserFindPasswordRequest dto);
    void certUser(UserCertificationRequest dto);
    void resetPassword(UserResetPasswordRequest dto);
}
