package com.sparta.java02.domain.purchase.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.wildfly.common.annotation.NotNull;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest {

    @NotNull
    Long userId;

    @NotNull
    List<PurchaseProductRequest> products;
}
