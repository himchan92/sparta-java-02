package com.sparta.java02.domain.product.service;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductTransactionServiceTest {

  @Autowired
  private ProductTransactionService productTransactionService;

  @Autowired
  private ProductRepository productRepository;

  // Transactional : 간결함, 비즈니스로직과분리, 유연한설정(직접 commit rollback 작성 생략 편리함)
  @Test
  void testUpdateProductStockSuccessTransactional() {
    //given
    Long productId = 201L;

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    //when
    productTransactionService.updateProductStockTransactional(product.getId(), 3);

    //then
    Product resultProduct = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    Assertions.assertEquals(product.getStock(), resultProduct.getStock() + 3);
  }

  @Test
  void testUpdateProductStockSuccess() {
    //given
    Long productId = 201L;

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    //when
    productTransactionService.updateProductStock(product.getId(), 3);

    //then
    Product resultProduct = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    assertThat(product.getStock()).isEqualTo(resultProduct.getStock() + 3);
  }
}