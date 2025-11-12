package com.example.demo.domain.product.service;

import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @Transactional
    public Product create(Product product) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return null;
    }

    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return null;
    }

    @Transactional
    public Product update(Long id, Product productDetails) {
        return null;
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
