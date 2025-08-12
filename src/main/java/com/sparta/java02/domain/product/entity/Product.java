package com.sparta.java02.domain.product.entity;

import com.sparta.java02.domain.category.entity.Category;
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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //TODO: 실습을 위한 임시 컬럼입니다. (실제론 이렇게 작업하면 안됩니다.)
    @Column(nullable = false)
    Long categoryId;

    @Column
    String name;

    @Column
    String description;

    @Column
    BigDecimal price;

    @Column
    Integer stock;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Builder
    public Product(Long categoryId, String name, String description, BigDecimal price, Integer stock) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
