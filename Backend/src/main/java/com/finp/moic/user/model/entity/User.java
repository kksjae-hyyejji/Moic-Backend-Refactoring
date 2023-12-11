package com.finp.moic.user.model.entity;

import com.finp.moic.giftCard.model.entity.Giftcard;
import com.finp.moic.userBookmark.model.entity.UserBookmark;
import com.finp.moic.card.model.entity.UserCard;
import com.finp.moic.util.entity.Base;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity(name="user")
@Table(indexes = {
        @Index(name = "user_id",columnList = "id"),
        @Index(name = "user_email",columnList = "email"),
        @Index(name = "user_delete", columnList = "deleted_at,is_delete"),
})
@Builder
@Getter
@SQLDelete(sql = "UPDATE user SET is_delete = true, deleted_at = CURRENT_TIMESTAMP WHERE user_seq = ?")
@Where(clause = "is_delete = false")
public class User extends Base {

    @Id
    @Column(name="user_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSeq;

    @Column(name="id", length = 20, unique = true, nullable = false)
    private String id;

    @Column(name="password", length = 500, nullable = false)
    private String password;

    @Column(name="name", length = 20, nullable = false)
    private String name;

    @Column(name="email", length = 30, nullable = false)
    private String email;

    @Column(name="gender", length = 10)
    private String gender;

    @Column(name="year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Giftcard> giftCards;

    @OneToMany(mappedBy = "user")
    private List<UserCard> userCards;

    @OneToMany(mappedBy = "user")
    private List<UserBookmark> userBookMarks;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    @Builder
    public User(long userSeq, String id, String password,
                String name, String email, String gender,
                int yearOfBirth, List<Giftcard> giftCards, List<UserCard> userCards,
                List<UserBookmark> userBookMarks) {
        this.userSeq = userSeq;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.yearOfBirth = yearOfBirth;
        this.giftCards = giftCards;
        this.userCards = userCards;
        this.userBookMarks = userBookMarks;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userSeq=" + userSeq +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
