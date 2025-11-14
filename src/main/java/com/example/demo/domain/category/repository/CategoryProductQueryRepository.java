package com.example.demo.domain.category.repository;

import com.example.demo.domain.category.dto.CategoryProductDTO;
import com.example.demo.domain.category.dto.QCategoryProductDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.demo.domain.category.entity.QCategory.category;
import static com.example.demo.domain.product.entity.QProduct.product;

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
