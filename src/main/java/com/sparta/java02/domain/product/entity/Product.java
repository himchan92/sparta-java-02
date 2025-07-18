package com.sparta.java02.domain.product.entity;

import com.sparta.java02.domain.category.entity.Category;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Table
@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  //TODO: 실습을  위해 임시로 주석처리
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  Category category;

  //TODO: 실습을 위한 임시 컬럼입니다. (실제론 이렇게 작업하면 안됩니다.), Long으로만 처리하면 별도 조회로직구현필요하여 JPA 답지않음
//  @Column(nullable = false)
//  Long categoryId;

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
  public Product(
      Category category,
      String name,
      String description,
      BigDecimal price,
      Integer stock
  ) {
    this.category = this.category;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
  }

  public void reduceStock(Integer quantity) {
    this.stock -= quantity;
  }

  public void increaseStock(Integer quantity) {
    this.stock += quantity;
  }
}
