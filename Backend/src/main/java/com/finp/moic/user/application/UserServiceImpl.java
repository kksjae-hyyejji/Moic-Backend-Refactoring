package com.finp.moic.user.application;


import com.finp.moic.user.adpater.in.request.*;
import com.finp.moic.user.application.port.in.UserUseCase;
import com.finp.moic.user.application.port.out.CommandUserPersistencePort;
import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.application.response.UserDetailResponse;
import com.finp.moic.user.application.response.UserFindIdResponse;
import com.finp.moic.user.application.response.UserLoginResponse;
import com.finp.moic.user.domain.User;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.*;
import com.finp.moic.user.security.dto.UserAuthentication;
import com.finp.moic.user.security.oauth.dto.AuthUserInfo;
import com.finp.moic.user.security.oauth.dto.OAuthUserInfo;
import com.finp.moic.user.security.oauth.util.HashUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserUseCase {

    private final CommandUserPersistencePort commandUserPersistencePort;
    private final QueryUserPersistencePort queryUserPersistencePort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SecurityRedisService securityRedisService;
    private final HashUtil hashUtil;
    private final JavaMailSender javaMailSender;
    private final CookieService cookieService;

    @Override
    public UserLoginResponse login(UserLoginRequest dto, HttpServletResponse httpResponse){
        // 만약 아이디가 조회되지 않으면
        User user = queryUserPersistencePort.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        //아이디는 조회 됐는데 비밀번호가 틀리면
        if(!user.getId().equals(dto.getId()) || !passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new NotFoundException(ExceptionEnum.USER_INVALID);
        }

        //로그인 하고 토큰에 id 저장
        String accessToken = jwtService.createAccessToken(user.getId());
        String refreshToken = jwtService.createRefreshToken();

        //Redis에 저장
        securityRedisService.setRefreshToken(user.getId(), refreshToken);

        //Cookie에 refresh 저장
        httpResponse.addCookie(cookieService.createCookie(refreshToken));

        return UserLoginResponse.builder()
                .name(user.getName())
                .accessToken(accessToken)
                .build();
    }

    @Override
    public void logout(UserAuthentication userAuthentication, String refreshToken){

        // 1. 인증 객체 지우기
        SecurityContextHolder.clearContext();

        // 2. Redis에서 refresh 지우기
        securityRedisService.deleteRefreshToken(userAuthentication.getId());
    }

    @Override
    public void regist(UserRegistRequest dto) {

        Optional<User> byId = queryUserPersistencePort.findById(dto.getId());
        if(byId.isPresent()){
            throw new AlreadyExistException(ExceptionEnum.USER_REGIST_DUPLICATE);
        }

        /*** Validation ***/
        if(!dto.getPassword().equals(dto.getPasswordCheck())){
            throw new ValidationException(ExceptionEnum.USER_REGIST_VALID);
        }

        /*** RDB Access ***/
        User user = User.builder()
                .id(dto.getId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .email(dto.getEmail())
                .gender(dto.getGender())
                .yearOfBirth(dto.getYearOfBirth())
                .build();

        commandUserPersistencePort.save(user);
    }

    @Override
    public boolean isIdValidate(UserIdCheckRequest dto){
        Optional<User> byId = queryUserPersistencePort.findById(dto.getId());
        if(byId.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailValidate(UserEmailCheckRequest dto){
        User user = queryUserPersistencePort.findByEmail(dto.getEmail());
        if(user!=null){
            return false;
        }
        return true;
    }

    @Override
    public void isPasswordValidate(String id, UserPasswordCheckRequest dto){
        User user = queryUserPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));
        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new NotFoundException(ExceptionEnum.USER_INVALID_PASSWORD);
        }
    }

    @Override
    public UserDetailResponse getUserDetail(String id){
        User user = queryUserPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        return UserDetailResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .yearOfBirth(user.getYearOfBirth())
                .build();
    }

    @Override
    @Transactional
    public void modifyUser(String id, UserModifyRequest dto){
        User user = queryUserPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        user.setGender(dto.getGender());
        user.setYearOfBirth(dto.getYearOfBirth());
    }

    @Override
    @Transactional
    public void modifyPassword(String id, UserModifyPasswordRequest dto){
        User user = queryUserPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        //비밀번호 두 개가 서로 다를 때
        if(!dto.getNewPassword().equals(dto.getNewPasswordCheck())){
            throw new ValidationException(ExceptionEnum.FORGERY_DATA);
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
    }

    @Override
    @Transactional
    public void deleteUser(String id){
        User user = queryUserPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        commandUserPersistencePort.delete(user);
    }

    @Override
    @Transactional
    public AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo) {

        // 유저가 존재하는지 확인
        User user = queryUserPersistencePort.findByEmail(oauthUserInfo.getEmail());

        if(user==null){
            user = User.builder()
                    .id(hashUtil.makeHashId())
                    .password(passwordEncoder.encode(hashUtil.makeHashPassword()))
                    .name(oauthUserInfo.getNickname())
                    .email(oauthUserInfo.getEmail())
                    .gender(null)
                    .yearOfBirth(0)
                    .build();


            commandUserPersistencePort.save(user);
        }
        return new AuthUserInfo(user.getId(), user.getEmail(), Arrays.asList("USER"));
    }

    @Override
    public UserFindIdResponse findId(UserFindIdRequest dto){
        User user = queryUserPersistencePort.findByNameAndEmail(dto.getName(), dto.getEmail());
        if(user==null){
            throw new NotFoundException(ExceptionEnum.USER_NOT_FOUND);
        }
        return UserFindIdResponse.builder()
                .id(user.getId())
                .build();
    }

    @Override
    @Transactional
    public void issueNumber(UserFindPasswordRequest dto){
        User user = queryUserPersistencePort.findByIdAndNameAndEmail(dto.getId(), dto.getName(), dto.getEmail());
        if(user==null) {
            throw new NotFoundException(ExceptionEnum.USER_NOT_FOUND);
        }
        try{
            String certNum = hashUtil.makeCertNumber();

            securityRedisService.setCertNumber(dto.getId(), certNum);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessageHelper.setTo(dto.getEmail());
            mimeMessageHelper.setSubject("[Moi'c] 회원 인증 번호");
            mimeMessageHelper.setFrom("moicsecurity@moic.com");
            mimeMessageHelper.setText(createMailForm(certNum),true);

            javaMailSender.send(mimeMessage);
        }catch (MessagingException e) {
            throw new MailSendException(ExceptionEnum.MAIL_SEND_ERROR);
        }

    }

    @Override
    public void certUser(UserCertificationRequest dto){
        String dbCertNumber = securityRedisService.getCertNumber(dto.getId());
        //ID가 위조 되었을 때
        if(dbCertNumber==null) {
            throw new ValidationException(ExceptionEnum.FORGERY_DATA);
        }

        //인증번호가 틀렸을 때
        if(!dto.getCertification().equals(dbCertNumber)){
            throw new ValidationException(ExceptionEnum.USER_CERT_ERROR);
        }

        // 맞으면 다시 3분으로 초기화
        securityRedisService.setCertTime(dto.getId());
    }

    @Override
    @Transactional
    public void resetPassword(UserResetPasswordRequest dto){
        /**
         * 1. 아이디 검증
         * 2. 인증번호 검증
         * 3. 비밀번호 두개 검증
         * 4. 저장
         * */
        User user = queryUserPersistencePort.findById(dto.getId())
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        String dbCertNumber = securityRedisService.getCertNumber(dto.getId());
        //ID가 위조 되었을 때
        if(dbCertNumber==null) {
            throw new ValidationException(ExceptionEnum.FORGERY_DATA);
        }

        //인증번호가 틀렸을 때
        if(!dto.getCertification().equals(dbCertNumber)){
            throw new ValidationException(ExceptionEnum.USER_CERT_ERROR);
        }

        //비밀번호 두 개가 서로 다를 때
        if(!dto.getPassword().equals(dto.getPasswordCheck())){
            throw new ValidationException(ExceptionEnum.FORGERY_DATA);
        }

        user.setPassword(passwordEncoder.encode(dto.getPassword()));
    }

    private String createMailForm(String value) {
        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1> 안녕하세요, Moi'c 입니다. </h1>";
        msg += "<br>";
        msg += "<p>인증번호 입니다.<p>";
        msg += "<br>";
        msg += "<p>감사합니다!<p>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3 style='color:blue;'>인증번호</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "<strong>";
        msg += value + "</strong><div><br/> ";
        msg += "</div>";
        msg += "</div>";
        msg += "</div>";
        return msg;
    }

}

