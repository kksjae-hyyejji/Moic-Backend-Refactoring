package com.finp.moic.giftCard.application.port.in;

import com.finp.moic.giftCard.application.response.GiftcardBrandServiceResponse;
import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.domain.Giftcard;
import com.finp.moic.user.model.entity.User;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.DeniedException;
import com.finp.moic.util.exception.list.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface GiftcardUseCase {

    public void regist (String id, MultipartFile multipartFile);
    public String parseShopName(String line);
    public LocalDate parseLocalDate(String line);

    public void delete(String id, String imageUrl);
    public List<GiftcardListServiceResponse> mygifts(String id);
}
