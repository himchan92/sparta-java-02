package com.example.demo.domain.category.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CategoryProductDTO {

    private final String categoryName;
    private final String productName;
    private final BigDecimal price;
    private final Integer stock;

    @QueryProjection
    public CategoryProductDTO(String categoryName, String productName, BigDecimal price, Integer stock) {
        this.categoryName = categoryName;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }
}
