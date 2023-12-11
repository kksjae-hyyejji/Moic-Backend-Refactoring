package com.finp.moic.auth.model.service;

import com.finp.moic.auth.model.dto.response.AuthRefreshResponseDTO;
import jakarta.servlet.http.HttpServletResponse;


public interface AuthService {

    AuthRefreshResponseDTO refresh(String accessToken, String refreshToken, HttpServletResponse httpResponse);

}
