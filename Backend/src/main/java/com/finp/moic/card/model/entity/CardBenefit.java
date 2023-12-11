package com.finp.moic.card.model.entity;

import com.finp.moic.card.model.entity.Card;
import com.finp.moic.util.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name="card_benefit")
@Table(indexes = {
        @Index(name="card_benefit_shop", columnList = "category, shop_name"),
        @Index(name = "cardbenefit_delete", columnList = "deleted_at,is_delete"),
})
@Getter
@Builder
@SQLDelete(sql = "UPDATE card_benefit SET is_delete = true, deleted_at = CURRENT_TIMESTAMP WHERE card_benefit_seq = ?")
@Where(clause = "is_delete = false")
public class CardBenefit extends Base {

    @Id
    @Column(name="card_benefit_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cardBenefitSeq;

    /* 혜지 : FK 확인 필요 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="card_name", referencedColumnName = "name")
    private Card card;

    @Column(name="category", length = 20, nullable = false)
    private String category;

    @Column(name="shop_name", length = 20, nullable = false)
    private String shopName;

    @Column(name="content", length = 100)
    private String content;

    @Column(name="discount", length = 20)
    private String discount;

    @Column(name="point", length = 20)
    private String point;

    @Column(name="cashback", length = 20)
    private String cashback;

    public CardBenefit() {
    }

    @Builder
    public CardBenefit(long cardBenefitSeq, Card card, String category,
                       String shopName, String content, String discount,
                       String point, String cashback) {
        this.cardBenefitSeq = cardBenefitSeq;
        this.card = card;
        this.category = category;
        this.shopName = shopName;
        this.content = content;
        this.discount = discount;
        this.point = point;
        this.cashback = cashback;
    }

    @Override
    public String toString() {
        return "CardBenefit{" +
                "cardBenefitSeq=" + cardBenefitSeq +
                ", card=" + card.getName() +
                ", category='" + category + '\'' +
                ", shopName='" + shopName + '\'' +
                ", content='" + content + '\'' +
                ", discount='" + discount + '\'' +
                ", point='" + point + '\'' +
                ", cashback='" + cashback + '\'' +
                '}';
    }
}
