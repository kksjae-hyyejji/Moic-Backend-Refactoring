package com.finp.moic.userBookmark.controller;

import com.finp.moic.userBookmark.model.dto.request.UserBookmarkDeleteRequestDTO;
import com.finp.moic.userBookmark.model.dto.request.UserBookmarkRegistRequestDTO;
import com.finp.moic.userBookmark.model.dto.response.UserBookmarkLookupResponseDTO;
import com.finp.moic.userBookmark.model.service.UserBookmarkService;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.util.security.dto.UserAuthentication;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/bkm")
public class UserBookmarkController {

    private final UserBookmarkService userBookmarkService;

    @Autowired
    public UserBookmarkController(UserBookmarkService userBookmarkService) {
        this.userBookmarkService = userBookmarkService;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseDTO> registBookmark(@RequestBody @Valid UserBookmarkRegistRequestDTO userBookmarkRegistRequestDTO,
                                                  @AuthenticationPrincipal UserAuthentication userAuthentication){

        userBookmarkService.registBookmark(userBookmarkRegistRequestDTO, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("북마크 등록이 완료되었습니다.")
                .build());
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteBookmarkList(@RequestBody @Valid UserBookmarkDeleteRequestDTO userBookmarkDeleteRequestDTO,
                                                  @AuthenticationPrincipal UserAuthentication userAuthentication){

        userBookmarkService.deleteBookmarkList(userBookmarkDeleteRequestDTO, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("북마크 삭제가 완료되었습니다.")
                .build());
    }

    @GetMapping("/lookup")
    public ResponseEntity<ResponseDTO> getBookmarkList(@AuthenticationPrincipal UserAuthentication userAuthentication){

        List<UserBookmarkLookupResponseDTO> dto=userBookmarkService.getBookmarkList(userAuthentication.getId());
        HashMap<String,Object> response=new HashMap<>();
        response.put("shopList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 북마크 조회")
                .data(response)
                .build());
    }

}
