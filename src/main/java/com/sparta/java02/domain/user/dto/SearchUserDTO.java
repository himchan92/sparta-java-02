package com.sparta.java02.domain.user.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchUserDTO {

  Long id;

  String name;

  String email;

  String password;

  LocalDateTime createdAt;

  LocalDateTime updatedAt;
}
