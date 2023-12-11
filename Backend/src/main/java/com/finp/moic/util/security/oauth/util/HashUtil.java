package com.finp.moic.util.security.oauth.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HashUtil {

    /**
     * 임시 아이디 발급 (소셜로그인 전용)
     */
    public String makeHashId() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 9);
        return uuid;
    }

    /**
     * 임시 비밀번호 발급 (소셜로그인 전용)
     * 영어 대소문자, 숫자, 특수문자 혼용
     * 12자 고정
     */
    public String makeHashPassword() {
        char[] special = { '!','@','#','$','%','^','&','*','(',')'};
        char[] upper = {'M', 'O', 'I', 'C'};

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, 9);

        int specialIdx = (int)(Math.random() * special.length);
        int upperIdx = (int)(Math.random() * upper.length);
        int num = (int)(Math.random() * 9);
        uuid.concat(Character.toString(special[specialIdx]));
        uuid.concat(Character.toString(upper[upperIdx]));
        uuid.concat(Integer.toString(num));
        return uuid;
    }

    
    /**
     * 임의의 인증번호 발급
     * 영문, 숫자
     * 6자리
     * */
    public String makeCertNumber(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid.substring(0,6);
    }
}
