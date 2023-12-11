package com.finp.moic.auth.model.service;

import com.finp.moic.auth.model.dto.response.AuthRefreshResponseDTO;
import com.finp.moic.util.cookie.CookieService;
import com.finp.moic.util.database.service.RedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.DeniedException;
import com.finp.moic.util.exception.list.TokenException;
import com.finp.moic.util.security.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthServiceImpl implements AuthService{

    private final JwtService jwtService;
    private final RedisService redisService;
    private final CookieService cookieService;

    @Autowired
    public AuthServiceImpl(JwtService jwtService, RedisService redisService, CookieService cookieService){
        this.jwtService = jwtService;
        this.redisService = redisService;
        this.cookieService = cookieService;
    }

    @Override
    public AuthRefreshResponseDTO refresh(String accessToken, String refreshToken, HttpServletResponse httpResponse) {

        /**
         * 1. 진짜 만료된 access Token인지 확인
         *      => 아니면 refresh 요청을 무한정으로 보낼 수 있기 때문
         * 2. refresh도 만료된건지 확인
         * 3. Redis에서도 확인
         * 4. access에서 회원 아이디 가져와서 Redis와 비교
         *
         * 위 과정이 모두 완료 되면 access 발급
         * */

        String newAccessToken = null;
        String userId = getUserId(accessToken);


        try{
            //만약 AccessToken이 만료되었다면
            jwtService.validateToken(accessToken);

            //아직 만료되지 않은 Access가 왔다면 그대로 반환
            return AuthRefreshResponseDTO.builder()
                    .token(accessToken)
                    .build();
        }catch (TokenException e){
            try{
                jwtService.validateToken(refreshToken);
            }catch (TokenException e2){
                //강제 로그아웃 후 재로그인 요청
                httpResponse.addCookie(cookieService.deleteCookie());
                SecurityContextHolder.clearContext();
                redisService.deleteRefreshToken(userId);
                throw new DeniedException(ExceptionEnum.RE_LOGIN);
            }
            //redis 검증
            String dbRefreshToken = redisService.getRefreshToken(userId);

            if(dbRefreshToken==null){
                httpResponse.addCookie(cookieService.deleteCookie());
                SecurityContextHolder.clearContext();
                throw new TokenException(ExceptionEnum.INVALID_TOKEN_ERROR);
            }
            //access 재발급
            newAccessToken = jwtService.createAccessToken(userId);
        }

        return AuthRefreshResponseDTO.builder()
                .token(newAccessToken)
                .build();
    }

    public String getUserId(String expiredAccessToken) {
        JSONParser jsonParser = new JSONParser();
        String[] split = expiredAccessToken.split("\\.");
        byte[] payLoad = split[1].getBytes();
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] userId = decoder.decode(payLoad);
        JSONObject jsonObject = null;
        try{
            jsonObject = (JSONObject) jsonParser.parse(new String(userId));
        }catch (ParseException e){
            throw new TokenException(ExceptionEnum.INVALID_TOKEN_ERROR);
        }
        return (String) jsonObject.get("sub");
    }
}
