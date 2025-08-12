package com.sparta.java02.domain.product.service;

import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

//  public Product create(Product product) {
//  }
//
//  public List<Product> getAll() {
//  }
//
//  public Product getById(Long id) {
//  }
//
//  public void delete(Long id) {
//  }
}
