package com.sparta.java02.common.exception;

import com.sparta.java02.common.enums.ServiceExceptionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceException extends RuntimeException {

    String code;
    String message;

    public ServiceException(ServiceExceptionCode response) {
        super(response.getMessage());
        this.code = response.name();
        this.message = super.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
