package com.finp.moic.util.database.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public S3Service(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public String uploadFile(MultipartFile multipartFile){

        if (multipartFile == null) throw new NotFoundException(ExceptionEnum.GIFTCARD_NOT_FOUND);

        String originalName = multipartFile.getOriginalFilename();
        String fileExtension = originalName.substring(originalName.lastIndexOf("."));
        String uniqueName = "Giftcard/" + UUID.randomUUID().toString() + fileExtension;
        long size = multipartFile.getSize();

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(size);

        try {
            amazonS3Client.putObject(new PutObjectRequest(bucket, uniqueName, multipartFile.getInputStream()
                    , objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(IOException e) {
            throw new NotFoundException(ExceptionEnum.GIFTCARD_NOT_FOUND);
        }


       return amazonS3Client.getUrl(bucket,uniqueName).toString();

    }

    public void deleteGiftcard(String imageUrl) {

        int lastIndexOfSlash = imageUrl.lastIndexOf('/');
        String imageName = "Giftcard/" + imageUrl.substring(lastIndexOfSlash + 1);
        amazonS3Client.deleteObject(bucket,imageName);
    }


}
