package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.common.enums.ServiceExceptionCode;
import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import com.sparta.java02.domain.purchase.dto.PurchaseRequest;
import com.sparta.java02.domain.purchase.dto.PurchaseResponse;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.entity.PurchaseProduct;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PurchaseResponse placePurchase(PurchaseRequest request) {
        //사용자조회
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

        //상품조회
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

        //재고확인및감소
        if(product.getStock() < request.getQuantity()) {
            throw new ServiceException(ServiceExceptionCode.NOT_FOUND_USER);
        }
        product.decreaseStock(request.getQuantity());

        //구매 및 구매품 생성 및 저장
        Purchase purchase = Purchase.builder()
                .user(user)
                .totalPrice(product.getPrice().multiply(new BigDecimal(request.getQuantity())))
                .status(PurchaseStatus.COMPLETED)
                .shippingAddress(request.getShippingAddress())
                .build();

        PurchaseProduct item = PurchaseProduct.builder()
                .purchase(purchase)
                .product(product)
                .quantity(request.getQuantity())
                .price(product.getPrice())
                .build();

        purchase.getPurchaseItems().add(item);

        Purchase savedPurchase = purchaseRepository.save(purchase);

        return null;
    }
}
