package com.sparta.java02.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.java02.domain.purchase.entity.Purchase;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected로 외부에서 사용 못 하게 막고, JPA 내부 동작만 허용
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //1(유저):N(물건들)
    @JsonBackReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Purchase> purchases = new ArrayList<>();

    @Column(nullable = false, length = 10)
    String name;

    @Column(nullable = false, unique = true)
    String email;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @CreationTimestamp //JPA 자동시간설정
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    LocalDateTime updatedAt;

    //생성자 Builder패턴: API 스펙상 필요한 값만 노출
    @Builder
    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
