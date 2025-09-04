package com.sparta.java02.domain.product.service;

import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor //생성자 DI final 필드대상 롬복 지원
public class ProductService {
    private final ProductRepository productRepository; //final 이기에 불변성 보장

    @Transactional
    public Product create(Product product) {
        return null;
    }

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepository.findById(id).get();
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
