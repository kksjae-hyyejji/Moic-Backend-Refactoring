package com.finp.moic.giftCard.application.port.in;

import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface GiftcardUseCase {

    public void regist (String id, MultipartFile multipartFile);
    public String parseShopName(String line);
    public LocalDate parseLocalDate(String line);

    public void delete(String id, String imageUrl);
    public List<GiftcardListServiceResponse> mygifts(String id);
}
