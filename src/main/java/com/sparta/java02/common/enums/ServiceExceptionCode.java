package com.sparta.java02.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ServiceExceptionCode {
    NOT_FOUND_PRODUCT("상품을 찾을 수 없습니다."),
    NOT_FOUND_USER("존재하지 않는 사용자"),
    DUPLICATE_EMAIL("중복된 이메일"),
    INSUFFICIENT_STOCK("상품의 재고가 부족합니다.");

    final String message;
}
