package com.finp.moic.shop.model.entity;

import com.finp.moic.userBookmark.model.entity.UserBookmark;
import com.finp.moic.util.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity(name="shop")
@Table(indexes = {
        @Index(name = "shop_delete", columnList = "deleted_at,is_delete"),
})
@Getter
@Builder
@SQLDelete(sql = "UPDATE shop SET is_delete = true, deleted_at = CURRENT_TIMESTAMP WHERE shop_seq = ?")
@Where(clause = "is_delete = false")
public class Shop extends Base {

    @Id
    @Column(name="shop_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shopSeq;

    @Column(name="main_category", length = 20, nullable = false)
    private String mainCategory;

    @Column(name="category", length = 20, nullable = false)
    private String category;

    @Column(name="name", length = 20, nullable = false)
    private String name;

    @Column(name="location", length = 20, nullable = false)
    private String location;

    @Column(name="address", length = 50, nullable = false)
    private String address;

    @Column(name="gu_name", length = 20, nullable = false)
    private String guName;

    @Column(name="latitude", nullable = false)
    private double latitude;

    @Column(name="longitude", nullable = false)
    private double longitude;

    @OneToMany(mappedBy = "shop")
    private List<UserBookmark> userBookMarks;

    public Shop() {
    }

    public Shop(long shopSeq, String mainCategory, String category,
                String name, String location, String address,
                String guName, double latitude, double longitude,
                List<UserBookmark> userBookMarks) {
        this.shopSeq = shopSeq;
        this.mainCategory = mainCategory;
        this.category = category;
        this.name = name;
        this.location = location;
        this.address = address;
        this.guName = guName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userBookMarks = userBookMarks;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopSeq=" + shopSeq +
                ", mainCategory='" + mainCategory + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                ", guName='" + guName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
