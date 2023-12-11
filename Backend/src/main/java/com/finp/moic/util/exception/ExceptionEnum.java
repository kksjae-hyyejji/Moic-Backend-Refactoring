package com.finp.moic.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionEnum {

    /* 혜지 : CODE 대문자 FIX 필요 */
    // 1. USER (U)
    USER_REGIST_DUPLICATE(HttpStatus.BAD_REQUEST,"U000","이미 존재하는 회원입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "존재하지 않는 사용자입니다."),
    USER_INVALID(HttpStatus.UNAUTHORIZED,"U002","아이디 혹은 비밀번호가 틀렸습니다."),
    USER_INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"U003","비밀번호가 틀렸습니다."),
    USER_REGIST_VALID(HttpStatus.BAD_REQUEST,"U004","입력 사항을 확인하세요."),
    USER_CERT_ERROR(HttpStatus.BAD_REQUEST,"U005","인증번호가 틀렸습니다."),

    // 2. SHOP (S)
    SHOP_NOT_FOUND(HttpStatus.NOT_FOUND,"S001","가맹점 조회에 실패했습니다."),
    SHOP_SEARCH_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"S002","가맹점 검색에 실패했습니다."),
    SHOP_SAVE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"S003","가맹점 정보 저장에 실패했습니다."),
    SHOP_RECOMMAND_ERROR(HttpStatus.BAD_REQUEST,"S004","경로 추천을 다시 시도해주세요."),

    // 3. CARD (C)
    /* 혜지 : 인덱스 테스트용 Exception */
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND,"C001","존재하지 않는 카드입니다."),
    CARD_REGIST_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"C002","카드 등록에 실패했습니다."),
    CARD_USER_NOT_FOUND(HttpStatus.NOT_FOUND,"C003","사용자 카드 조회에 실패했습니다."),
    CARD_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"C004","카드 삭제에 실패했습니다."),
    CARD_REGIST_DUPLICATE(HttpStatus.BAD_REQUEST,"C005","이미 등록된 카드입니다."),

    // 4. GIFT CARD (G)
    GIFTCARD_NOT_FOUND(HttpStatus.NOT_FOUND, "G001","등록된 기프트콘이 없습니다."),
    GIFTCATD_INVALID(HttpStatus.BAD_REQUEST, "G002", "올바르지 않은 기프티콘 이미지입니다."),
    GIFTCATD_REGIST_ERROR(HttpStatus.BAD_REQUEST, "G003", "해당 기프티콘은 문의해주세요."),

    // 5. CARD BENEFIT (CB)

    // 6. USER BOOKMARK (UB)
    BOOKMARK_REGIST_DUPLICATE(HttpStatus.BAD_REQUEST,"UB001","이미 등록된 북마크입니다."),
    BOOKMARK_NOT_FOUND(HttpStatus.BAD_REQUEST,"UB002","존재하지 않는 북마크입니다."),
    BOOKMARK_DELETE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"UB003","북마크 삭제에 실패했습니다."),

    // 7. SERVER ERROR (E)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E001", "서버 에러가 발생했습니다."),
    MAIL_SEND_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"E002", "메일 전송에 실패하였습니다."),

    // 8. SECURITY ERROR(SE)
    EXPIRED_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "SE001", "토큰이 만료되었습니다."),
    INVALID_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "SE002", "토큰이 유효하지 않습니다."),
    RE_LOGIN(HttpStatus.UNAUTHORIZED, "SE003", "다시 로그인 해주세요."),
    HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "SE004", "접근권한이 없습니다."),
    UNAUTHENTICATED_MEMBER(HttpStatus.UNAUTHORIZED,"SE005","인증되지 않은 사용자입니다."),
    FORGERY_DATA(HttpStatus.BAD_REQUEST,"SE006","유효한 값이 아닙니다."),

    // 9. Valid(V)
    NOT_VALID_ERROR(HttpStatus.BAD_REQUEST,"V001","유효하지 않은 값입니다.");

    private final HttpStatus status;
    private final String errorCode;
    private String errorMessage;

    ExceptionEnum(HttpStatus status, String errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
