package com.example.demo.domain.purchase.service;

import com.example.demo.domain.product.repository.ProductRepository;
import com.example.demo.domain.purchase.repository.PurchaseRepository;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
}
