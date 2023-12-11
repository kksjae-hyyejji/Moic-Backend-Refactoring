package com.finp.moic.userBookmark.model.entity;

import com.finp.moic.shop.model.entity.Shop;
import com.finp.moic.user.model.entity.User;
import com.finp.moic.util.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity(name="user_bookmark")
@Table(indexes = {
        @Index(name = "userbookmark_delete", columnList = "deleted_at,is_delete"),
})
@Getter
@Builder
@SQLDelete(sql = "UPDATE user_bookmark SET is_delete = true, deleted_at = CURRENT_TIMESTAMP WHERE user_bookmark_seq = ?")
@Where(clause = "is_delete = false")
public class UserBookmark extends Base {

    @Id
    @Column(name="user_bookmark_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userBookmarkSeq;

    /* 혜지 : FK 확인 필요 (양방향) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    /* 혜지 : FK 확인 필요 (양방향) */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_seq", referencedColumnName = "shop_seq")
    private Shop shop;

    public UserBookmark() {
    }

    @Builder
    public UserBookmark(long userBookmarkSeq, User user, Shop shop) {
        this.userBookmarkSeq = userBookmarkSeq;
        this.user = user;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "UserBookmark{" +
                "userBookmarkSeq=" + userBookmarkSeq +
                ", user=" + user.getId() +
                ", shop=" + shop.getName() +
                '}';
    }
}
