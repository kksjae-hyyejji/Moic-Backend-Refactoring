package com.finp.moic.user.application.port.in;

import com.finp.moic.user.application.response.AuthRefreshResponse;
import jakarta.servlet.http.HttpServletResponse;


public interface AuthUseCase {

    AuthRefreshResponse refresh(String accessToken, String refreshToken, HttpServletResponse httpResponse);

}
