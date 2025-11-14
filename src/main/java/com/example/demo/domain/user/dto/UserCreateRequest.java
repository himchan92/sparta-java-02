package com.example.demo.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequest {

    @NotBlank(message = "사용자 이름은 필수고 공백안됩니다.")
    @Size(min = 2, max = 50, message = "이름은 2~50자 범위내로 입력하세요")
    String username;

    @NotBlank(message = "이메일은 필수")
    @Email(message = "유효한 이메일 형식이 아닙니다.")
    String email;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다.")
    String phoneNumber;
}