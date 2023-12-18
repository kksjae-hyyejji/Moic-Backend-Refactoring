package com.finp.moic.giftCard.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public interface RegistGiftcardUseCase {

    void regist (String id, MultipartFile multipartFile);

    String parseShopName(String line);

    LocalDate parseLocalDate(String line);

}
