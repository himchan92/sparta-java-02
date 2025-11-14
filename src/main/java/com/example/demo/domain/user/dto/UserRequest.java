package com.example.demo.domain.user.dto;

import com.example.demo.domain.user.entity.User;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    @NotNull
    String username;

    @Email
    String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    String passwordHash;
}