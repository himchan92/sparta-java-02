package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.domain.product.repository.ProductRepository;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
}
