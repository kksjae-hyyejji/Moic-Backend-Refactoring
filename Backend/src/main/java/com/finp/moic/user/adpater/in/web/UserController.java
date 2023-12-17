package com.finp.moic.user.adpater.in.web;


import com.finp.moic.user.adpater.in.request.*;
import com.finp.moic.user.application.response.UserDetailResponse;
import com.finp.moic.user.application.response.UserFindIdResponse;
import com.finp.moic.user.application.response.UserLoginResponse;
import com.finp.moic.user.application.port.in.UserUseCase;
import com.finp.moic.user.application.CookieService;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.user.security.dto.UserAuthentication;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserUseCase userUseCase;
    private final CookieService cookieService;

    @Autowired
    public UserController(UserUseCase userUseCase, CookieService cookieService) {
        this.userUseCase = userUseCase;
        this.cookieService = cookieService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseDTO> login(
            @RequestBody @Valid UserLoginRequest dto,
            HttpServletResponse httpResponse
    ){
        UserLoginResponse response = userUseCase.login(dto, httpResponse);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("로그인에 성공했습니다.")
                .data(response)
                .build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(
            @AuthenticationPrincipal UserAuthentication userAuthentication,
            @CookieValue(name = "refreshToken") String refreshToken,
            HttpServletResponse httpResponse
    ){
        userUseCase.logout(userAuthentication,refreshToken);

        httpResponse.addCookie(cookieService.deleteCookie());
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("로그아웃 성공")
                .build());
    }

    @PostMapping(value = "/regist")
    public ResponseEntity<ResponseDTO> regist(
            @RequestBody @Valid UserRegistRequest dto
    ){
        userUseCase.regist(dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("회원가입 성공")
                .build());
    }

    @PostMapping("/check/id")
    public ResponseEntity<ResponseDTO> isIdValidate(
            @RequestBody @Valid UserIdCheckRequest dto
    ){

        if(!Pattern.matches("^[a-z0-9]{6,12}$",dto.getId())){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                    .message(" ")
                    .build());
        }

        boolean isAble = userUseCase.isIdValidate(dto);

        if(!isAble){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                    .message("중복된 ID입니다.")
                    .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("사용가능한 ID입니다.")
                .build());
    }

    @PostMapping("/check/email")
    public ResponseEntity<ResponseDTO> isEmailValidate(
            @RequestBody @Valid UserEmailCheckRequest dto
    ){

        if(!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",dto.getEmail())){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                    .message(" ")
                    .build());
        }

        boolean isAble = userUseCase.isEmailValidate(dto);

        if(!isAble){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                    .message("중복된 email입니다.")
                    .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("사용가능한 email입니다.")
                .build());
    }

    @PostMapping("/check/password")
    public ResponseEntity<ResponseDTO> isPasswordValidate(
            @AuthenticationPrincipal UserAuthentication userAuthentication,
            @RequestBody @Valid UserPasswordCheckRequest dto
            ){
        userUseCase.isPasswordValidate(userAuthentication.getId(), dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("회원 정보 확인이 완료되었습니다.")
                .build());
    }

    @PostMapping("/detail")
    public ResponseEntity<ResponseDTO> getUserDetail(
            @AuthenticationPrincipal UserAuthentication userAuthentication
    ){
        UserDetailResponse response = userUseCase.getUserDetail(userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("회원 정보 조회")
                .data(response)
                .build());
    }

    @PostMapping("/modify")
    public ResponseEntity<ResponseDTO> modifyUser(
            @AuthenticationPrincipal UserAuthentication userAuthentication,
            @RequestBody UserModifyRequest dto
    ){
        userUseCase.modifyUser(userAuthentication.getId(), dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("회원 정보 수정이 완료되었습니다.")
                .build());
    }

    @PostMapping("/modify/password")
    public ResponseEntity<ResponseDTO> modifyPassword(
            @AuthenticationPrincipal UserAuthentication userAuthentication,
            @RequestBody UserModifyPasswordRequest dto
    ){
        userUseCase.modifyPassword(userAuthentication.getId(), dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("비밀번호 변경이 완료되었습니다!")
                .build());
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteUser(
            @AuthenticationPrincipal UserAuthentication userAuthentication
    ){
        userUseCase.deleteUser(userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("회원 탈퇴가 완료되었습니다.")
                .build());
    }

    @PostMapping("/lookup/id")
    public ResponseEntity<ResponseDTO> findId(
            @RequestBody @Valid UserFindIdRequest dto
    ){
        UserFindIdResponse response = userUseCase.findId(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("회원님의 아이디를 찾았아요!")
                .data(response)
                .build());
    }

    @PostMapping("/temp/password")
    public ResponseEntity<ResponseDTO> issueNumber(
            @RequestBody @Valid UserFindPasswordRequest dto
    ){
        userUseCase.issueNumber(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("이메일이 발송되었습니다.")
                .build());
    }

    @PostMapping("/verify/password")
    public ResponseEntity<ResponseDTO> certUser(
            @RequestBody @Valid UserCertificationRequest dto
    ){
        userUseCase.certUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("인증되었습니다.")
                .build());
    }

    @PostMapping("/reset/password")
    public ResponseEntity<ResponseDTO> resetPassword(
            @RequestBody @Valid UserResetPasswordRequest dto
    ){
        userUseCase.resetPassword(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("비밀번호 재설정이 완료되었습니다.")
                .build());
    }

}