package com.sparta.java02.domain.purchase.repository;

import com.sparta.java02.domain.purchase.entity.PurchaseProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {

  //purchaseId 조회 의미로 purchaseId가 있어야 가능
  List<PurchaseProduct> findByPurchase_Id(Long purchaseId);
}
