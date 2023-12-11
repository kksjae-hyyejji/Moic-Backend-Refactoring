package com.finp.moic.giftCard.controller;

import com.finp.moic.giftCard.model.dto.request.GiftcardDeleteRequestDTO;
import com.finp.moic.giftCard.model.dto.response.GiftcardListResponseDTO;
import com.finp.moic.giftCard.model.service.GiftcardServiceImpl;
import com.finp.moic.util.database.service.S3Service;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.util.exception.BusinessException;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.security.dto.UserAuthentication;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    private final GiftcardServiceImpl giftcardService;
    private final S3Service s3Service;

    @Autowired
    public GiftcardController(GiftcardServiceImpl giftcardService, S3Service s3Service) {
        this.giftcardService = giftcardService;
        this.s3Service = s3Service;
    }

    @PostMapping("/regist")
    @Transactional
    public ResponseEntity<ResponseDTO> regist(@RequestParam(value = "file", required = false)
                                                  MultipartFile multipartFile,
                                                @AuthenticationPrincipal UserAuthentication userAuthentication
    ,HttpServletRequest httpServletRequest) {

        System.out.println("============================");
        System.out.println(httpServletRequest.getContentType());
        giftcardService.regist(userAuthentication.getId(),multipartFile);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("등록이 완료되었습니다.")
                .build());
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> delete(@RequestBody GiftcardDeleteRequestDTO giftcardDeleteRequestDTO,
                                              @AuthenticationPrincipal UserAuthentication userAuthentication) {

        giftcardService.delete(userAuthentication.getId(),giftcardDeleteRequestDTO.getImageUrl());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("삭제가 완료되었습니다.")
                .build());
    }

    @GetMapping("/mygifts")
    public ResponseEntity<ResponseDTO> mygifts(@AuthenticationPrincipal UserAuthentication userAuthentication) {

        List<GiftcardListResponseDTO> list = giftcardService.mygifts(userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 기프티콘 목록 조회")
                .data(list)
                .build());
    }



}
