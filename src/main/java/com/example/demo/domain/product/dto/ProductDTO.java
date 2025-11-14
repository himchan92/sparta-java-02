package com.example.demo.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductDTO {

    private final String name;
    private final BigDecimal price;
    private final Integer stock;

    @QueryProjection //QueryDSL DTO 생성하기위한 인식 어노테이션
    public ProductDTO(String name, BigDecimal price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}