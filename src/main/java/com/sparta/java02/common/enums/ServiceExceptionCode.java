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
    DUPLICATE_EMAIL("이메일이 중복됩니다."),
    INSUFFICIENT_STOCK("재고가 맞지않습니다."),
    NOT_FOUND_USER("사용자를 찾을 수 없습니다.");

    final String message;
}
