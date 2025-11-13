package com.example.demo.domain.product.controller;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.domain.product.dto.ProductRequest;
import com.example.demo.domain.product.dto.ProductResponse;
import com.example.demo.domain.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    //TODO: 전체 상품조회
    @GetMapping
    public ApiResponse<List<ProductResponse>> getAll() {
        return ApiResponse.success(productService.getAll());
    }

    //TODO: 단일 상품 조회
    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
        return ApiResponse.success(productService.getById(id));
    }

    //TODO: 상품 생성
    @PostMapping
    public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ApiResponse.success(productService.create(request));
    }

    //TODO: 상품 수정
    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ApiResponse.success(productService.update(id, request));
    }

    //TODO: 상품 삭제
    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ApiResponse.success();
    }
}