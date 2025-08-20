package com.sparta.java02.domain.purchase.dto;

import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.user.entity.User;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseResponse {
    User user;

    BigDecimal totalPrice;

    PurchaseStatus status;

    String shippingAddress;
}
