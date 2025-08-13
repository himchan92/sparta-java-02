package com.sparta.java02.domain.purchase.entity;

import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity //JPA 엔티티명시
@Getter
@DynamicInsert //null 아닌것만 insert
@DynamicUpdate //변경대상만 update
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //DB Table user_id 컬럼과 조인
    User user;

    //가격관련 컬럼은 BigDecimal 타입 권장
    @Column(name = "total_price", nullable = false)
    BigDecimal totalPrice;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING) //열거형 필수지정
    PurchaseStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Builder
    public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
