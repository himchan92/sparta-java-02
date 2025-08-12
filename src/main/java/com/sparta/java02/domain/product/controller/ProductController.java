package com.sparta.java02.domain.product.controller;

import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

//  @PostMapping
//  public Product create(@RequestBody Product product) {
//    return productService.create(product);
//  }
//
//  @GetMapping
//  public List<Product> getAll() {
//    return productService.getAll();
//  }
//
//  @GetMapping("/{id}")
//  public Product findById(@PathVariable Long id) {
//    return productService.getById(id);
//  }
//
//  @PutMapping("/{id}")
//  public Product update(@PathVariable Long id, @RequestBody Product product) {
//    return productService.update(id, productDetails);
//  }
//
//  @DeleteMapping("/{id}")
//  public void delete(@PathVariable Long id) {
//    productService.delete(id);
//  }
}
