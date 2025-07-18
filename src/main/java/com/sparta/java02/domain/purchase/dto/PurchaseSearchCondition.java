package com.sparta.java02.domain.purchase.dto;

public class PurchaseSearchCondition {
  private Long customerId; // 검색할 고객 ID
  private String status;   // 검색할 구매 상태
  private int limit;       // 페이지 당 데이터 수
  private int offset;      // 건너뛸 데이터 수
}