package com.finp.moic.giftCard.application;

import com.finp.moic.giftCard.application.port.in.GiftcardUseCase;
import com.finp.moic.giftCard.application.port.out.*;
import com.finp.moic.giftCard.application.port.out.external.ChatGptPort;
import com.finp.moic.giftCard.application.port.out.external.NaverOcrPort;
import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.domain.Giftcard;
import com.finp.moic.giftCard.application.response.GiftcardBrandServiceResponse;
import com.finp.moic.user.model.entity.User;
import com.finp.moic.user.model.repository.UserRepository;
import com.finp.moic.util.exception.list.DeniedException;
import com.finp.moic.giftCard.adapter.out.external.ChatGptAdapater;
import com.finp.moic.giftCard.adapter.out.external.NaverOcrAdapater;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftcardServiceImpl implements GiftcardUseCase {

    private final CommandImageS3Port commandImageS3Port;
    private final NaverOcrPort naverOcrPort;
    private final ChatGptPort chatGptPort;
    private final UserRepository userRepository;
    private final QueryGiftcardPersistencePort queryGiftcardPersistencePort;
    private final QueryGiftcardBrandPersistencePort queryGiftcardBrandPersistencePort;
    private final CommandGiftcardPersistencePort commandGiftcardPersistencePort;
    private final CommandGiftcardBrandPersistencePort commandGiftcardBrandPersistencePort;

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

        User user = userRepository.findById(id)
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

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */

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

    @Override
    @CacheEvict(value="giftcardList", key="#id")
    public void delete(String id, String imageUrl) {

        Giftcard giftcard = queryGiftcardPersistencePort.findByImageUrl(imageUrl)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.GIFTCARD_NOT_FOUND));
        commandImageS3Port.deleteGiftcard(imageUrl);
        commandGiftcardPersistencePort.delete(giftcard);

    }

    @Override
    @Cacheable(value="giftcardList", key="#id")
    public List<GiftcardListServiceResponse> mygifts(String id) {

        return queryGiftcardPersistencePort.findAllByUserId(id);
    }
}
