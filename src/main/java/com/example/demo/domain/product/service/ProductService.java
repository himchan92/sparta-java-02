package com.example.demo.domain.product.service;

import com.example.demo.common.exception.ServiceException;
import com.example.demo.common.exception.ServiceExceptionCode;
import com.example.demo.domain.category.entity.Category;
import com.example.demo.domain.category.repository.CategoryRepository;
import com.example.demo.domain.product.dto.ProductRequest;
import com.example.demo.domain.product.dto.ProductResponse;
import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<ProductResponse> getAll() {
        return null;
    }

    @Transactional(readOnly = true)
    public ProductResponse getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        return ProductResponse.builder()
                .id(product.getId())
                .category(product.getCategory())
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
    }
}
