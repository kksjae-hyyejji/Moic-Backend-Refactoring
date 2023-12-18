package com.finp.moic.card.adapter.in.web;

import com.finp.moic.card.adapter.in.request.CardDeleteRequest;
import com.finp.moic.card.adapter.in.request.CardRegistRequest;
import com.finp.moic.card.application.port.in.DeleteCardUseCase;
import com.finp.moic.card.application.port.in.DetailCardUseCase;
import com.finp.moic.card.application.port.in.GetCardListUseCase;
import com.finp.moic.card.application.port.in.RegistCardUseCase;
import com.finp.moic.card.application.response.CardAllServiceReponse;
import com.finp.moic.card.application.response.CardDetailServiceResponse;
import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.application.response.CardServiceResponse;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.user.security.dto.UserAuthentication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Validated
public class CardController {

    private final RegistCardUseCase registCardUseCase;
    private final GetCardListUseCase getCardListUseCase;
    private final DeleteCardUseCase deleteCardUseCase;
    private final DetailCardUseCase detailCardUseCase;

    @PostMapping("/regist")
    public ResponseEntity<ResponseDTO> registCard(@RequestBody @Valid CardRegistRequest cardRegistRequest,
                                                    @AuthenticationPrincipal UserAuthentication userAuthentication){

        registCardUseCase.registCard(cardRegistRequest, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                        .message("카드 등록이 완료되었습니다.")
                        .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getCardList(@AuthenticationPrincipal UserAuthentication userAuthentication){

        CardAllServiceReponse response= getCardListUseCase.getCardList(userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("전체 카드 목록 조회")
                .data(response)
                .build());
    }

    @GetMapping("/mycards")
    public ResponseEntity<ResponseDTO> getMyCardList(@AuthenticationPrincipal UserAuthentication userAuthentication) {

        List<CardMineServiceResponse> dto= getCardListUseCase.getMyCardList(userAuthentication.getId());
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

        deleteCardUseCase.deleteCard(cardDeleteRequest,userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                        .message("카드 삭제가 완료되었습니다.")
                        .build());
    }

    @GetMapping("/detail")
    public ResponseEntity<ResponseDTO> detailCard(@RequestParam("cardName") @NotBlank String cardName){

        CardDetailServiceResponse response= detailCardUseCase.detailCard(cardName);

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

        List<CardServiceResponse> dto= getCardListUseCase.searchCard(company,type,cardName,userAuthentication.getId());
        HashMap<String,Object> response=new HashMap<>();
        response.put("cardList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("카드 검색")
                .data(response)
                .build());
    }
}
