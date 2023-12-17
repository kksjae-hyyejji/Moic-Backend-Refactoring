package com.finp.moic.card.adapter.in.web;

import com.finp.moic.card.adapter.in.request.CardDeleteRequest;
import com.finp.moic.card.adapter.in.request.CardRegistRequest;
import com.finp.moic.card.application.response.CardAllServiceReponse;
import com.finp.moic.card.application.response.CardDetailServiceResponse;
import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.application.response.CardServiceResponse;
import com.finp.moic.card.application.port.in.CardUseCase;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.user.security.dto.UserAuthentication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/card")
@Validated
public class CardController {

    private final CardUseCase cardUseCase;

    @Autowired
    public CardController(CardUseCase cardUseCase) {
        this.cardUseCase = cardUseCase;
    }

    @PostMapping("/regist")
    public ResponseEntity<ResponseDTO> registCard(@RequestBody @Valid CardRegistRequest cardRegistRequest,
                                                    @AuthenticationPrincipal UserAuthentication userAuthentication){

        cardUseCase.registCard(cardRegistRequest, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                        .message("카드 등록이 완료되었습니다.")
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getCardList(@AuthenticationPrincipal UserAuthentication userAuthentication){

        CardAllServiceReponse response= cardUseCase.getCardList(userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("전체 카드 목록 조회")
                .data(response)
                .build());
    }

    @GetMapping("/mycards")
    public ResponseEntity<ResponseDTO> getMyCardList(@AuthenticationPrincipal UserAuthentication userAuthentication) {

        List<CardMineServiceResponse> dto= cardUseCase.getMyCardList(userAuthentication.getId());
        HashMap<String, List<CardMineServiceResponse>> response=new HashMap<>();
        response.put("cardList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 카드 목록 조회")
                .data(response)
                .build());
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestBody @Valid CardDeleteRequest cardDeleteRequest,
                                                    @AuthenticationPrincipal UserAuthentication userAuthentication){

        cardUseCase.deleteCard(cardDeleteRequest,userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                        .message("카드 삭제가 완료되었습니다.")
                        .build());
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseDTO> detailCard(@RequestParam("cardName") @NotBlank String cardName){

        CardDetailServiceResponse response= cardUseCase.detailCard(cardName);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 카드 상세 조회")
                .data(response)
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> searchCard(@RequestParam("company") @NotNull String company,
                                                  @RequestParam("type") @NotNull String type,
                                                  @RequestParam("cardName") @NotNull String cardName,
                                                    @AuthenticationPrincipal UserAuthentication userAuthentication ){

        List<CardServiceResponse> dto= cardUseCase.searchCard(company,type,cardName,userAuthentication.getId());
        HashMap<String,Object> response=new HashMap<>();
        response.put("cardList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("카드 검색")
                .data(response)
                .build());
    }
}
