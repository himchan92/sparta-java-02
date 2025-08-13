package com.sparta.java02.domain.product.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  //전체 상품조회
  @GetMapping
  public ApiResponse<List<ProductResponse>> getAll() {
    return ApiResponse.success(productService.getAll());
  }

  //단일 상품조회
  @GetMapping("/{id}")
  public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
    return ApiResponse.success(productService.getById(id));
  }

  //상품생성
  @PostMapping
  public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    return ApiResponse.success(productService.create(request));
  }

  //상품수정
  @PostMapping("/{id}")
  public ApiResponse<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
    return ApiResponse.success(productService.update(id, request));
  }

  //상품삭제
  @DeleteMapping("/{id}")
  public ApiResponse<Void> delete(@PathVariable Long id) {
    productService.delete(id);
    return ApiResponse.success();
  }
}
