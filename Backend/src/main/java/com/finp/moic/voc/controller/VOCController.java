package com.finp.moic.voc.controller;

import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.util.security.dto.UserAuthentication;
import com.finp.moic.voc.model.dto.request.VOCRequestDTO;
import com.finp.moic.voc.model.service.VOCService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voc")
public class VOCController {

    private final VOCService vocService;

    @Autowired
    public VOCController(VOCService vocService){
        this.vocService = vocService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> sendVOC(
            @AuthenticationPrincipal UserAuthentication userAuthentication,
            @RequestBody @Valid VOCRequestDTO dto
    ){
        vocService.sendVOC(userAuthentication.getId(), dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("VOC 전송 완료")
                .build());
    }
}
