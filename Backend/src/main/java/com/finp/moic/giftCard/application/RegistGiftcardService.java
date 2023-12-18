package com.finp.moic.giftCard.application;

import com.finp.moic.giftCard.application.port.in.RegistGiftcardUseCase;
import com.finp.moic.giftCard.application.port.out.CommandGiftcardPersistencePort;
import com.finp.moic.giftCard.application.port.out.CommandImageS3Port;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardBrandPersistencePort;
import com.finp.moic.giftCard.application.port.out.external.ChatGptPort;
import com.finp.moic.giftCard.application.port.out.external.NaverOcrPort;
import com.finp.moic.giftCard.application.response.GiftcardBrandServiceResponse;
import com.finp.moic.giftCard.domain.Giftcard;
import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.domain.User;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.DeniedException;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistGiftcardService implements RegistGiftcardUseCase {

    private final CommandImageS3Port commandImageS3Port;
    private final NaverOcrPort naverOcrPort;
    private final ChatGptPort chatGptPort;
    private final QueryUserPersistencePort queryUserPersistencePort;
    private final CommandGiftcardPersistencePort commandGiftcardPersistencePort;
    private final QueryGiftcardBrandPersistencePort queryGiftcardBrandPersistencePort;

    @Override
    @CacheEvict(value="giftcardList", key="#id")
    public void regist (String id, MultipartFile multipartFile){

        String filePath = commandImageS3Port.uploadFile(multipartFile);
        String originalName = multipartFile.getOriginalFilename();
        List<String> texts =
                naverOcrPort.naverOcrApi(filePath, originalName.substring(originalName.lastIndexOf(".") + 1));

        String content = chatGptPort.response(texts);

        String[] lines = content.split("\n");
        String shopName= parseShopName(lines[0]);
        LocalDate localDate = parseLocalDate(lines[1]);

        User user = queryUserPersistencePort.findById(id)
                .orElseThrow(()-> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));


        GiftcardBrandServiceResponse categoryDTO= queryGiftcardBrandPersistencePort.findByName(shopName);
        if(categoryDTO==null){
            throw new DeniedException(ExceptionEnum.GIFTCATD_REGIST_ERROR);
        }

        Giftcard giftcard = Giftcard.builder()
                .user(user)
                .mainCategory(categoryDTO.getMainCategory())
                .category(categoryDTO.getCategory())
                .shopName(shopName)
                .imageUrl(filePath)
                .dueDate(localDate)
                .build();

        commandGiftcardPersistencePort.save(giftcard);

    }

    @Override
    public String parseShopName(String line) {
        return line.replace("상호명:","").trim();
    }

    @Override
    public LocalDate parseLocalDate(String line) {
        String dataStr = line.replace("유효기간:","").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dataStr,formatter);
        return localDate;
    }
}
