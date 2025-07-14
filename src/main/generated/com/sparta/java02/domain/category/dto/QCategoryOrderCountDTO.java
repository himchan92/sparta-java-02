package com.sparta.java02.domain.category.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.sparta.java02.domain.category.dto.QCategoryOrderCountDTO is a Querydsl Projection type for CategoryOrderCountDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCategoryOrderCountDTO extends ConstructorExpression<CategoryOrderCountDTO> {

    private static final long serialVersionUID = 1206354183L;

    public QCategoryOrderCountDTO(com.querydsl.core.types.Expression<String> categoryName, com.querydsl.core.types.Expression<Long> orderCount) {
        super(CategoryOrderCountDTO.class, new Class<?>[]{String.class, long.class}, categoryName, orderCount);
    }

}

