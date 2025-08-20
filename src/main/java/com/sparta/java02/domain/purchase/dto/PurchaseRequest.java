package com.sparta.java02.domain.purchase.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest {
    Long userId;

    Long productId;

    Integer quantity;

    String shippingAddress;
}