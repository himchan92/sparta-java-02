package com.sparta.java02.domain.product.service;

import com.sparta.java02.common.enums.ServiceExceptionCode;
import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.domain.product.dto.ProductRequest;
import com.sparta.java02.domain.product.dto.ProductResponse;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return new ArrayList<>();
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        //서비스단내에서 예외처리도 가능
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .build();
    }

    @Transactional
    public ProductResponse create(@Valid ProductRequest request) {
        return null;
    }

    @Transactional
    public ProductResponse update(Long id, @Valid ProductRequest request) {
        return null;
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}