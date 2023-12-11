package com.finp.moic.user.model.service;


import com.finp.moic.user.model.dto.request.*;
import com.finp.moic.user.model.dto.response.*;
import com.finp.moic.util.security.dto.UserAuthentication;
import com.finp.moic.util.security.oauth.dto.AuthUserInfo;
import com.finp.moic.util.security.oauth.dto.OAuthUserInfo;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    UserLoginResponseDTO login(UserLoginRequestDTO dto, HttpServletResponse httpResponse);
    void regist(UserRegistRequestDTO dto);
    boolean isIdValidate(UserIdCheckRequestDTO dto);
    boolean isEmailValidate(UserEmailCheckRequestDTO dto);
    void isPasswordValidate(String id, UserPasswordCheckRequestDTO dto);
    void logout(UserAuthentication userAuthentication,String refreshToken);
    UserDetailResponseDTO getUserDetail(String id);
    void modifyUser(String id, UserModifyRequestDTO dto);
    void modifyPassword(String id, UserModifyPasswordRequestDTO dto);
    void deleteUser(String id);
    AuthUserInfo getOrRegisterUser(OAuthUserInfo oAuthUserInfo);
    UserFindIdResponseDTO findId(UserFindIdRequestDTO dto);
    void issueNumber(UserFindPasswordRequestDTO dto);
    void certUser(UserCertificationRequestDTO dto);
    void resetPassword(UserResetPasswordRequestDTO dto);
}
