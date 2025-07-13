package com.sparta.java02.domain.product.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java02.domain.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

import static com.sparta.java02.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepository {

    private final JPAQueryFactory queryFactory;

    public List<Product> searchProducts(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        return queryFactory
                .selectFrom(product)
                .where(
                        nameContains(name),
                        priceGoe(minPrice),
                        priceLoe(maxPrice)
                )
                .fetch();
    }

    private BooleanExpression nameContains(String name) {
        // 이름 파라미터가 null 이나 공백이 아니면, 이름 포함 조건(like)을 반환
        return StringUtils.hasText(name) ? product.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression priceGoe(BigDecimal minPrice) {
        // 최소 가격 파라미터가 null이 아니면, 해당 가격 이상(goe) 조건을 반환
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression priceLoe(BigDecimal maxPrice) {
        // 최대 가격 파라미터가 null이 아니면, 해당 가격 이하(loe) 조건을 반환
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }
}
