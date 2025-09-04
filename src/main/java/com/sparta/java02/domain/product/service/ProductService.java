package com.sparta.java02.domain.product.service;

import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor //생성자 DI final 필드대상 롬복 지원
public class ProductService {
    private final ProductRepository productRepository; //final 이기에 불변성 보장

    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return null;
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(Integer id) {
        return null;
    }

    @Transactional
    public ProductResponse create(@Valid ProductRequest request) {
        return null;
    }

    @Transactional
    public ProductResponse update(Integer id, @Valid ProductRequest request) {
        return null;
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}