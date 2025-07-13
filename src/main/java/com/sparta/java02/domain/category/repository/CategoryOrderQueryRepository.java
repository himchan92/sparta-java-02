package com.sparta.java02.domain.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java02.domain.category.dto.CategoryOrderCountDTO;
import com.sparta.java02.domain.category.dto.QCategoryOrderCountDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.java02.domain.product.entity.QProduct.product;
import static com.sparta.java02.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.java02.domain.purchase.entity.QPurchaseItem.purchaseItem;

@Repository
@RequiredArgsConstructor
public class CategoryOrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<CategoryOrderCountDTO> findCategoryOrderCounts() {
        return queryFactory
                .select(new QCategoryOrderCountDTO(
                        product.category.name,
                        purchase.id.countDistinct() //고유한 ID 갯수 세기 -> select count(distinct id) from table
                ))
                .from(purchaseItem)
                .join(purchaseItem.purchase, purchase)
                .join(purchaseItem.product, product)
                .groupBy(product.category.name) //카테고리 이름으로 그룹화
                .orderBy(purchase.id.countDistinct().desc())
                .fetch();
    }
}
