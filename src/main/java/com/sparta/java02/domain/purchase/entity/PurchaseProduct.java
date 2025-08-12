package com.sparta.java02.domain.purchase.entity;

import com.sparta.java02.domain.common.enums.PurchaseStatus;
import com.sparta.java02.domain.product.entity.Product;
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
@Table(name = "purchase_product")
public class PurchaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    Product product;

    @Builder
    public PurchaseProduct(Product product, Purchase purchase) {
        this.product = product;
        this.purchase = purchase;
    }
}
