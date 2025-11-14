package com.example.demo.domain.product.repository;

import com.example.demo.domain.product.dto.ProductDTO;
import com.example.demo.domain.product.dto.QProductDTO;
import com.example.demo.domain.product.entity.Product;
import static com.example.demo.domain.product.entity.QProduct.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Product> findProducts(String name, Double minPrice, Double maxPrice) {
        return queryFactory
                .selectFrom(product)
                .where(
                        nameContains(name),
                        priceGoe(minPrice),
                        priceLoe(maxPrice)
                )
                .fetch();
    }

    //TODO: DTO 사용 조회
    public List<ProductDTO> findProductDTOs(Double minPrice) {
        return queryFactory
                .select(new QProductDTO(
                        product.name,
                        product.price,
                        product.stock
                ))
                .from(product)
                .where(product.price.goe(minPrice))
                .fetch();
    }

    // 조건들을 메서드로 분리
    private BooleanExpression nameContains(String name) {
        return name != null ? product.name.contains(name) : null;
    }

    private BooleanExpression priceGoe(Double minPrice) {
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression priceLoe(Double maxPrice) {
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }
}
