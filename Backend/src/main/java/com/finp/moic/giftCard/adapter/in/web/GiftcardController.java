package com.finp.moic.giftCard.adapter.in.web;

import com.finp.moic.giftCard.adapter.in.request.GiftcardDeleteRequestDTO;
import com.finp.moic.giftCard.application.port.in.GiftcardUseCase;
import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.application.GiftcardServiceImpl;
import com.finp.moic.util.database.service.S3Service;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.util.security.dto.UserAuthentication;
import jakarta.servlet.http.HttpServletRequest;
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
public class GiftcardController {

    private final GiftcardUseCase giftcardUseCase;
    private final S3Service s3Service;

    @Autowired
    public GiftcardController(GiftcardUseCase giftcardUseCase, S3Service s3Service) {
        this.giftcardUseCase = giftcardUseCase;
        this.s3Service = s3Service;
    }

    @PostMapping("/regist")
    @Transactional
    public ResponseEntity<ResponseDTO> regist(@RequestParam(value = "file", required = false)
                                                  MultipartFile multipartFile,
                                                @AuthenticationPrincipal UserAuthentication userAuthentication
    ,HttpServletRequest httpServletRequest) {

        giftcardUseCase.regist(userAuthentication.getId(),multipartFile);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("등록이 완료되었습니다.")
                .build());
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> delete(@RequestBody GiftcardDeleteRequestDTO giftcardDeleteRequestDTO,
                                              @AuthenticationPrincipal UserAuthentication userAuthentication) {

        giftcardUseCase.delete(userAuthentication.getId(),giftcardDeleteRequestDTO.getImageUrl());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("삭제가 완료되었습니다.")
                .build());
    }

    @GetMapping("/mygifts")
    public ResponseEntity<ResponseDTO> mygifts(@AuthenticationPrincipal UserAuthentication userAuthentication) {

        List<GiftcardListServiceResponse> list = giftcardUseCase.mygifts(userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 기프티콘 목록 조회")
                .data(list)
                .build());
    }



}
