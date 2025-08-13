package com.sparta.java02.domain.user.mapper;

import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //ENTITY -> DTO 변환
    UserResponse toResponse(User user);

    //DTO -> ENTITY 변환
    User toEntity(UserCreateRequest request);
}
