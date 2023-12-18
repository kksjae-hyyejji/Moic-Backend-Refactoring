package com.finp.moic.giftCard.adapter.in.web;

import com.finp.moic.giftCard.adapter.in.request.GiftcardDeleteRequestDTO;
import com.finp.moic.giftCard.application.RegistGiftcardService;
import com.finp.moic.giftCard.application.port.in.DeleteGiftcardUseCase;
import com.finp.moic.giftCard.application.port.in.GetMyGiftcardUseCase;
import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.adapter.out.persistence.ImageS3Adapter;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.user.security.dto.UserAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/gift")
@RequiredArgsConstructor
public class GiftcardController {

    private final DeleteGiftcardUseCase deleteGiftcardUseCase;
    private final GetMyGiftcardUseCase getMyGiftcardUseCase;
    private final RegistGiftcardService registGiftcardService;

    @PostMapping("/regist")
    @Transactional
    public ResponseEntity<ResponseDTO> regist(@RequestParam(value = "file", required = false)
                                                  MultipartFile multipartFile,
                                                @AuthenticationPrincipal UserAuthentication userAuthentication
    ,HttpServletRequest httpServletRequest) {

        registGiftcardService.regist(userAuthentication.getId(),multipartFile);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("등록이 완료되었습니다.")
                .build());
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> delete(@RequestBody GiftcardDeleteRequestDTO giftcardDeleteRequestDTO,
                                              @AuthenticationPrincipal UserAuthentication userAuthentication) {

        deleteGiftcardUseCase.delete(userAuthentication.getId(),giftcardDeleteRequestDTO.getImageUrl());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("삭제가 완료되었습니다.")
                .build());
    }

    @GetMapping("/mygifts")
    public ResponseEntity<ResponseDTO> mygifts(@AuthenticationPrincipal UserAuthentication userAuthentication) {

        List<GiftcardListServiceResponse> list = getMyGiftcardUseCase.mygifts(userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 기프티콘 목록 조회")
                .data(list)
                .build());
    }



}
