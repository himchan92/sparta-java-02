package com.sparta.java02.domain.purchase.entity;

import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.domain.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

@Table(name = "purchase")
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected로 외부에서 사용 못 하게 막고, JPA 내부 동작만 허용
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
  private List<PurchaseProduct> purchaseItems = new ArrayList<>();

  //FK 관리하는쪽이 연관관계 주인 -> Purchase
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id") //User FK
  User user;

  @Column(nullable = false)
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  PurchaseStatus status;

  String shippingAddress;

  @CreationTimestamp //JPA 자동시간설정
  @Column(name = "created_at", nullable = false, updatable = false)
  LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  LocalDateTime updatedAt;

  @Builder
  public Purchase(User user, BigDecimal totalPrice, PurchaseStatus status, String shippingAddress) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
    this.shippingAddress = shippingAddress;
  }

    public void addPurchaseItem(PurchaseProduct item) {
        purchaseItems.add(item);
        item.setPurchase(this);
    }
}
