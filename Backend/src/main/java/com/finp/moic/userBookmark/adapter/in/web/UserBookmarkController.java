package com.finp.moic.userBookmark.adapter.in.web;

import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkDeleteRequest;
import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkRegistRequest;
import com.finp.moic.userBookmark.application.port.in.DeleteBookmarkUseCase;
import com.finp.moic.userBookmark.application.port.in.GetBookmarkUseCase;
import com.finp.moic.userBookmark.application.port.in.RegistBookmarkUseCase;
import com.finp.moic.userBookmark.application.response.UserBookmarkLookupServiceResponse;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.user.security.dto.UserAuthentication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bkm")
@RequiredArgsConstructor
public class UserBookmarkController {

    private final DeleteBookmarkUseCase deleteBookmarkUseCase;
    private final GetBookmarkUseCase getBookmarkUseCase;
    private final RegistBookmarkUseCase registBookmarkUseCase;

    @PostMapping("/regist")
    public ResponseEntity<ResponseDTO> registBookmark(@RequestBody @Valid UserBookmarkRegistRequest userBookmarkRegistRequest,
                                                  @AuthenticationPrincipal UserAuthentication userAuthentication){

        registBookmarkUseCase.registBookmark(userBookmarkRegistRequest, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("북마크 등록이 완료되었습니다.")
                .build());
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteBookmarkList(@RequestBody @Valid UserBookmarkDeleteRequest userBookmarkDeleteRequest,
                                                  @AuthenticationPrincipal UserAuthentication userAuthentication){

        deleteBookmarkUseCase.deleteBookmarkList(userBookmarkDeleteRequest, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("북마크 삭제가 완료되었습니다.")
                .build());
    }

    @GetMapping("/lookup")
    public ResponseEntity<ResponseDTO> getBookmarkList(@AuthenticationPrincipal UserAuthentication userAuthentication){

        List<UserBookmarkLookupServiceResponse> dto= getBookmarkUseCase.getBookmarkList(userAuthentication.getId());
        HashMap<String,Object> response=new HashMap<>();
        response.put("shopList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 북마크 조회")
                .data(response)
                .build());
    }

}
