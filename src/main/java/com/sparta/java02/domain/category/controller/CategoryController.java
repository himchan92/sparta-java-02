package com.sparta.java02.domain.category.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.category.dto.CategoryOrderCountDTO;
import com.sparta.java02.domain.category.repository.CategoryOrderQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryOrderQueryRepository categoryOrderQueryRepository;

    @GetMapping("/order-counts")
    public ApiResponse<List<CategoryOrderCountDTO>> orderCounts() {
        return ApiResponse.success(categoryOrderQueryRepository.findCategoryOrderCounts());
    }

}
