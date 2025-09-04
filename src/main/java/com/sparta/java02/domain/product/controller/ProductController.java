package com.sparta.java02.domain.product.controller;

import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.service.ProductService;
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

//    @GetMapping
//    public ResponseEntity<List<ProductResponse>> getAll() {
//        return ResponseEntity.ok(productService.getAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(productService.getById(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductResponse> update(@PathVariable Integer id, @Valid @RequestBody ProductRequest request) {
//        return ResponseEntity.ok(productService.update(id, request));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        productService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
