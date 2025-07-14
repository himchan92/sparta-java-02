package com.sparta.java02.domain.purchase.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.purchase.dto.PurchaseCancelRequest;
import com.sparta.java02.domain.purchase.dto.PurchaseCancelResponse;
import com.sparta.java02.domain.purchase.dto.PurchaseRequest;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.purchase.repository.PurchaseRepository;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseService {

  private final PurchaseProcessService purchaseProcessService;
  private final PurchaseCancelService cancelService;
  private final PurchaseRepository purchaseRepository;
  private final UserRepository userRepository;

  @Transactional
  public Purchase purchase(PurchaseRequest request) {
    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));

    return purchaseProcessService.process(user, request.getItems());
  }

  @Transactional
  public PurchaseCancelResponse cancelPurchase(PurchaseCancelRequest request) {
    return cancelService.cancelPurchase(request.getPurchaseId(), request.getUserId());
  }
}
