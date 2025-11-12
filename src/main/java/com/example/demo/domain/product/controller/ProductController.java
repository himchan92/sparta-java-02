package com.example.demo.domain.product.controller;

import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    //TODO: @RequestBody 통해서 전달
    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    //TODO: 전체조회
    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    //TODO: 단일 항목 조회
    @GetMapping("{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    //TODO: 특정상품 수정
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.update(id, productDetails);
    }

    //TODO: 특정상품삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
