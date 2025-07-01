package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import com.sparta.java02.domain.purchase.dto.PurchaseProductRequest;
import com.sparta.java02.domain.purchase.dto.PurchaseRequest;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.entity.PurchaseProduct;
import com.sparta.java02.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.repository.UserRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final ProductRepository productRepository;
  private final PurchaseProductRepository purchaseProductRepository;
  private final UserRepository userRepository;

  @Transactional
  public Purchase purchase(PurchaseRequest request) {
    //구매 생성
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    Purchase purchase = purchaseRepository.save(Purchase.builder()
        .user(user)
        .build());

    BigDecimal totalPrice = BigDecimal.ZERO;
    List<PurchaseProduct> purchaseProducts = new ArrayList<>();

    //구매 상품 처리
    for (PurchaseProductRequest itemRequest : request.getPurchaseItems()) {
      Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow();

      if (itemRequest.getQuantity() > product.getStock()) {
        throw new ServiceException(ServiceExceptionCode.OUT_OF_STOCK_PRODUCT);
      }

      product.reduceStock(itemRequest.getQuantity());

      PurchaseProduct purchaseProduct = PurchaseProduct.builder()
          .product(product)
          .purchase(purchase)
          .quantity(itemRequest.getQuantity())
          .price(product.getPrice())
          .build();

      purchaseProducts.add(purchaseProduct);
      totalPrice = totalPrice.add(
          product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
    }

    //구매정보 업데이트 및 저장
    purchase.setTotalPrice(totalPrice);
    purchaseProductRepository.saveAll(purchaseProducts);

    return purchase;
  }
}
