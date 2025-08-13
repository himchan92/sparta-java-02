package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.domain.product.repository.ProductRepository;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Transactional
    public void createPurchase(Long userId, Long productId, int quantity) {
        //유저정보 얻기위해 유저도메인 가져다 사용
        User user = userService.getUserById(userId);
    }
}
