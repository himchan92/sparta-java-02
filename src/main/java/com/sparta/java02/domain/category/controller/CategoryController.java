package com.sparta.java02.domain.category.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.category.dto.CategoryOrderCountDTO;
import com.sparta.java02.domain.category.dto.CategoryRequest;
import com.sparta.java02.domain.category.repository.CategoryOrderQueryRepository;
import com.sparta.java02.domain.category.service.CategoryJdbcService;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryOrderQueryRepository categoryOrderQueryRepository;
    private final CategoryJdbcService categoryJdbcService;

    @GetMapping("/order-counts")
    public ApiResponse<List<CategoryOrderCountDTO>> orderCounts() {
        return ApiResponse.success(categoryOrderQueryRepository.findCategoryOrderCounts());
    }

    @PatchMapping("/{id}/name")
    public ApiResponse<JSONObject> updateByName(@RequestParam Long id, @RequestBody CategoryRequest request) throws SQLException {
        categoryJdbcService.updateCategory(id ,request.getName());

        return ApiResponse.success(new JSONObject());
    }

}
