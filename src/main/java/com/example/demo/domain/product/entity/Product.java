package com.example.demo.domain.product.entity;

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

@Table(name = "product")
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Long categoryId; //실습위한 임시처리로 실무에서는 비추

    @Column(nullable = false)
    String name;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false)
    Integer stock;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
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
