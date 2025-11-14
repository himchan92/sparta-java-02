package com.example.demo.domain.product.repository;

import com.example.demo.domain.product.entity.Product;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductSearchRepository {

    private final JPAQueryFactory queryFactory;

    public List<Product> searchProducts(String name, Double minPrice, Double maxPrice) {
        return queryFactory
                .selectFrom(product)
                .where(

                )
                .fetch();
    }

    //TODO: querydsl 제공 BooleanExpression 권장
    private BooleanExpression nameContains(String name) {
        // 이름 파라미터가 null이나 공백이 아니면, 이름 포함 조건(like)을 반환
        return StringUtils.hasText(name) ? product.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression priceGoe(Double minPrice) {
        // 최소 가격 파라미터가 null이 아니면, 해당 가격 이상(goe) 조건을 반환
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression priceLoe(Double maxPrice) {
        // 최대 가격 파라미터가 null이 아니면, 해당 가격 이하(loe) 조건을 반환
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }
}
