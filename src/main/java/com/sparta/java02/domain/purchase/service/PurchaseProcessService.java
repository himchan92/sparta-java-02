package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.enums.PurchaseStatus;
import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import com.sparta.java02.domain.purchase.dto.PurchaseProductRequest;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.entity.PurchaseProduct;
import com.sparta.java02.domain.purchase.repository.PurchaseProductRepository;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseProcessService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final PurchaseProductRepository purchaseProductRepository;
    private final UserRepository userRepository;

    @Transactional
    public Purchase process(User user, List<PurchaseProductRequest> purchaseItems) {
        Purchase purchase = createAndSavePurchase(user);
        List<PurchaseProduct> purchaseProducts = createAndProcessPurchaseProducts(purchaseItems, purchase);
        BigDecimal totalPrice = calculateTotalPrice(purchaseProducts);

        purchase.setTotalPrice(totalPrice);

        return purchase;
    }

    //무슨역할인지 명시되게 메소드명 정할것
    private Purchase createAndSavePurchase(User user) {
        return purchaseRepository.save(Purchase.builder()
                .user(user)
                .totalPrice(BigDecimal.ZERO)
                .status(PurchaseStatus.PENDING)
                .build()
        );
    }

    private List<PurchaseProduct> createAndProcessPurchaseProducts(List<PurchaseProductRequest> itemRequests, Purchase purchase) {
        List<PurchaseProduct> purchaseProducts = new ArrayList<>();

        for(PurchaseProductRequest itemRequest : itemRequests) {
            Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow();

            //재고수량체크
            validateStock(product, itemRequest.getQuantity());
            product.reduceStock(itemRequest.getQuantity());

            PurchaseProduct purchaseProduct = PurchaseProduct.builder()
                    .product(product)
                    .purchase(purchase)
                    .quantity(itemRequest.getQuantity())
                    .price(product.getPrice())
                    .build();

            purchaseProducts.add(purchaseProduct);
        }

        purchaseProductRepository.saveAll(purchaseProducts);
        return purchaseProducts;
    }

    private void validateStock(Product product, Integer requestedQuantity) {
        if(requestedQuantity > product.getStock()) {
            throw new ServiceException(ServiceExceptionCode.OUT_OF_STOCK_PRODUCT);
        }
    }

    private BigDecimal calculateTotalPrice(List<PurchaseProduct> purchaseProducts) {
        return purchaseProducts.stream()
                .map(purchaseproduct -> purchaseproduct.getPrice()
                        .multiply(BigDecimal.valueOf(purchaseproduct.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
