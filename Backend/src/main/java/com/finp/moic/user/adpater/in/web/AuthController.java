package com.finp.moic.user.adpater.in.web;

import com.finp.moic.user.adpater.in.request.AuthRefreshRequest;
import com.finp.moic.user.application.response.AuthRefreshResponse;
import com.finp.moic.user.application.port.in.AuthUseCase;
import com.finp.moic.util.dto.ResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase){
        this.authUseCase = authUseCase;
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseDTO> refresh(
            @RequestBody AuthRefreshRequest dto,
            @CookieValue(name = "refreshToken", required = false) String refreshToken,
            HttpServletResponse httpResponse
    ){

        AuthRefreshResponse response = authUseCase.refresh(dto.getAccessToken(), refreshToken, httpResponse);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("Refresh")
                .data(response)
                .build());
    }
}
