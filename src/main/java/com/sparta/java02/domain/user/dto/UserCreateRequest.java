package com.sparta.java02.domain.user.dto;

import com.sparta.java02.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequest {
    @NotBlank(message = "사용자이름은 필수입니다.")
    @Size(min = 2, max = 50, message = "이름은 2~50자 사이로 입력")
    String name;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일아닙니다.")
    String email;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-XXXX-XXXX 형식이어야 합니다.")
    String phoneNumber;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    String password;

    public User toEntity(String encodePassword) {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .passwordHash(encodePassword)
                .build();
    }
}
