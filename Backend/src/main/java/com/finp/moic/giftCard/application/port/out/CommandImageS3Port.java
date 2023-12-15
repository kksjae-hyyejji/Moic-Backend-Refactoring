package com.finp.moic.giftCard.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface CommandImageS3Port {

    String uploadFile(MultipartFile multipartFile);

    void deleteGiftcard(String imageUrl);

}
