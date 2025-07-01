package com.sparta.java02.domain.purchase.entity;

import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Table
@Entity
@Getter //Getter는 거의 필수고 Setter는 함부로 오픈할수없기에 특정 오픈허용된 필드에만 별도 선언
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Purchase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  User user;

  @Column
  BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  PurchaseStatus status;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  LocalDateTime createdAt;

//  @Column
//  @UpdateTimestamp
//  LocalDateTime updatedAt;

  @Builder
  public Purchase(
      User user,
      BigDecimal totalPrice,
      PurchaseStatus status
  ) {
    this.user = user;
    this.totalPrice = totalPrice;
    this.status = status;
  }

  public void setStatus(PurchaseStatus status) {
    if (!ObjectUtils.isEmpty(status)) {
      this.status = status;
    }
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice.add(totalPrice);
  }

  // Setter를 막고, 의미 있는 메서드로 상태 변경을 제어
  public void cancelPurchase() {
    if (this.status != PurchaseStatus.PENDING) {
      throw new ServiceException(ServiceExceptionCode.CANNOT_CANCEL);
    }
    this.status = PurchaseStatus.CANCELED;
  }
}
