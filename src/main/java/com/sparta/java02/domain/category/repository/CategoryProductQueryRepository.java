package com.sparta.java02.domain.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java02.domain.category.dto.CategoryProductDTO;
import com.sparta.java02.domain.category.dto.QCategoryProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.sparta.java02.domain.category.entity.QCategory.category;
import static com.sparta.java02.domain.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class CategoryProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<CategoryProductDTO> findCategoryProducts(String categoryName) {
        return queryFactory
                .select(new QCategoryProductDTO(
                        category.name,
                        product.name,
                        product.price,
                        product.stock
                ))
                .from(product)
                .join(product.category, category)
                .where(category.name.eq(categoryName))
                .fetch();
    }
}
