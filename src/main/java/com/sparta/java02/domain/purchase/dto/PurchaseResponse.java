package com.sparta.java02.domain.purchase.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseResponse {
    Long purchaseId;
    String username;
    BigDecimal totalPrice;
}
