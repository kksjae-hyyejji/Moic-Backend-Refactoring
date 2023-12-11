package com.finp.moic.giftCard.model.service;

import com.finp.moic.giftCard.model.dto.response.GiftcardBrandResponseDTO;
import com.finp.moic.giftCard.model.dto.response.GiftcardListResponseDTO;
import com.finp.moic.giftCard.model.entity.Giftcard;
import com.finp.moic.giftCard.model.repository.jpa.GiftcardBrandRepository;
import com.finp.moic.giftCard.model.repository.jpa.GiftcardRepository;
import com.finp.moic.user.model.entity.User;
import com.finp.moic.user.model.repository.UserRepository;
import com.finp.moic.util.exception.list.DeniedException;
import com.finp.moic.util.service.ChatGptService;
import com.finp.moic.util.service.NaverOcrService;
import com.finp.moic.util.database.service.S3Service;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GiftcardServiceImpl{

    private final S3Service s3Service;
    private final NaverOcrService naverOcrService;
    private final ChatGptService chatGptService;
    private final UserRepository userRepository;
    private final GiftcardRepository giftcardRepository;
    private final GiftcardBrandRepository giftcardBrandRepository;

    @Autowired
    public GiftcardServiceImpl(S3Service s3Service, NaverOcrService naverOcrService,
                               ChatGptService chatGptService, UserRepository userRepository, GiftcardRepository giftcardRepository,
                               GiftcardBrandRepository giftcardBrandRepository) {
        this.s3Service = s3Service;
        this.naverOcrService = naverOcrService;
        this.chatGptService = chatGptService;
        this.userRepository = userRepository;
        this.giftcardRepository = giftcardRepository;
        this.giftcardBrandRepository = giftcardBrandRepository;
    }

    @CacheEvict(value="giftcardList", key="#id")
    public void regist (String id, MultipartFile multipartFile){

        String filePath = s3Service.uploadFile(multipartFile);
        String originalName = multipartFile.getOriginalFilename();
        List<String> texts =
                naverOcrService.naverOcrApi(filePath, originalName.substring(originalName.lastIndexOf(".") + 1));

        String content = chatGptService.response(texts);

        String[] lines = content.split("\n");
        String shopName= parseShopName(lines[0]);
        LocalDate localDate = parseLocalDate(lines[1]);

        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(ExceptionEnum.USER_NOT_FOUND));


        GiftcardBrandResponseDTO categoryDTO=giftcardBrandRepository.findByName(shopName);
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

        giftcardRepository.save(giftcard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */

    }

    public String parseShopName(String line) {
        return line.replace("상호명:","").trim();
    }
    public LocalDate parseLocalDate(String line) {
        String dataStr = line.replace("유효기간:","").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dataStr,formatter);
        return localDate;
    }

    @CacheEvict(value="giftcardList", key="#id")
    public void delete(String id, String imageUrl) {

        Giftcard giftcard = giftcardRepository.findByImageUrl(imageUrl)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.GIFTCARD_NOT_FOUND));
        s3Service.deleteGiftcard(imageUrl);
        giftcardRepository.delete(giftcard);

    }

    @Cacheable(value="giftcardList", key="#id")
    public List<GiftcardListResponseDTO> mygifts(String id) {

        return giftcardRepository.findAllByUserId(id);
    }
}
