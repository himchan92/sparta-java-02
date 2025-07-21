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
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductTransactionManager {

  private final PlatformTransactionManager transactionManager;
  private final ProductRepository productRepository;

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
