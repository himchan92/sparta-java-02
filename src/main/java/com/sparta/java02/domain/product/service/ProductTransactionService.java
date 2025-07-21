package com.sparta.java02.domain.product.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.product.entity.Product;
import com.sparta.java02.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductTransactionService {

  private final PlatformTransactionManager transactionManager;
  private final ProductRepository productRepository;

  @Transactional(readOnly = true) //선언적 트랜젝션 : 별도 commit rollback 작업 직접 안하구 자동처리해줘서 비즈니스로직 집중가능
  public Product getProduct(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));
  }

  @Transactional
  public void updateProductStockTransactional(Long productId, int quantity) {
    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_PRODUCT));

    if(product.getStock() < quantity) {
      throw new ServiceException(ServiceExceptionCode.OUT_OF_STOCK_PRODUCT);
    }

    product.reduceStock(quantity);

    //실제 트랜젝션 활성화되어있는지 여부 호출
    log.info("isTransaction : {}", TransactionSynchronizationManager.isActualTransactionActive());
  }

  public void updateProductStock(Long productId, Integer stock) {
    TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

    try {
      Product product = productRepository.findById(productId)
          .orElseThrow(() -> new ServiceException(ServiceExceptionCode.OUT_OF_STOCK_PRODUCT));

      if(product.getStock() < stock) {
        throw new IllegalArgumentException("Insufficient stock");
      }

      product.reduceStock(stock);
      productRepository.save(product);

      log.info("isTransaction : {}", TransactionSynchronizationManager.isActualTransactionActive());

      transactionManager.commit(status);
    } catch (Exception e) {
      transactionManager.rollback(status);
      throw e;
    }
  }
}
