package com.sparta.java02.domain.product.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.category.dto.CategoryProductDTO;
import com.sparta.java02.domain.category.repository.CategoryProductQueryRepository;
import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductSearchRepository;
import com.sparta.java02.domain.product.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/products")
public class ProductControllerV3 {

  private final ProductService productService;
  private final ProductSearchRepository productSearchRepository;
  private final CategoryProductQueryRepository categoryProductQueryRepository;

  //전체 상품 조회
  @GetMapping
  public ApiResponse<List<ProductResponse>> getAll() {
    return ApiResponse.success(productService.getAll());
  }

  //단일 상품 조회
  @GetMapping("/{id}")
  public ApiResponse<ProductResponse> getById(@PathVariable Long id) {
    return ApiResponse.success(productService.getById(id));
  }

  //상품 등록
  @PostMapping
  public ApiResponse<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    return ApiResponse.success(productService.create(request));
  }

  //상품 수정
  @PutMapping("/{id}")
  public ApiResponse<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
    return ApiResponse.success(productService.update(id, request));
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> delete(@PathVariable Long id) {
    productService.delete(id);

    return ApiResponse.success();
  }

  @GetMapping("/category/{categoryName}")
  public ApiResponse<List<CategoryProductDTO>> searchCategoryProducts(@PathVariable String categoryName) {
    return ApiResponse.success(categoryProductQueryRepository.findCategoryProducts(categoryName));
  }

  @GetMapping("/search")
  public ApiResponse<List<Product>> search(@RequestParam(required = false) String name, BigDecimal minPrice, BigDecimal maxPrice) {
    return ApiResponse.success(productSearchRepository.searchProducts(name, minPrice, maxPrice));
  }
}
