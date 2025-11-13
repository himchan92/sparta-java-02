package com.example.demo.domain.product.dto;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    Long categoryId;

    @NotNull
    String name;

    String description;

    @NotNull
    @Positive
    BigDecimal price;

    @NotNull
    @PositiveOrZero
    Integer stock;
}
