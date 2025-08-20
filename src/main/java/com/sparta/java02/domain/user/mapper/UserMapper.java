package com.sparta.java02.domain.user.mapper;

import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);

    User toEntity(UserCreateRequest request);
}
