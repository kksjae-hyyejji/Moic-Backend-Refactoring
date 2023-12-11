package com.finp.moic.giftCard.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Entity(name="giftcard_brand")
@Table(indexes = {
        @Index(name="giftcard_brand_shop",columnList = "main_category,category,name"),
})
@Getter
@Builder
@ToString
/* 혜지 : 직접 관리하는 Entity이므로 Base 상속 X, Soft Delete X */
public class GiftcardBrand {

    @Id
    @Column(name="giftcard_brand_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long giftcardBrandSeq;

    @Column(name="main_category", length = 50, nullable = false)
    private String mainCategory;

    @Column(name="category", length = 50, nullable = false)
    private String category;

    @Column(name="name", length = 50, nullable = false, unique = true)
    private String name;

    public GiftcardBrand() {
    }

    public GiftcardBrand(long giftcardBrandSeq, String mainCategory, String category,
                         String name) {
        this.giftcardBrandSeq = giftcardBrandSeq;
        this.mainCategory = mainCategory;
        this.category = category;
        this.name = name;
    }
}
