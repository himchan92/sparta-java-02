package com.sparta.java02.domain.purchase.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sparta.java02.domain.purchase.enums.PurchaseStatus;
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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(nullable = false)
    BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    PurchaseStatus status;

    @Column(nullable = false, updatable = false, name = "created_at")
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @Builder
    public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status) {
        this.user = user;
        this.totalPrice = totalPrice;
        this.status = status;
    }
}
