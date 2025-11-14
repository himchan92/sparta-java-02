package com.example.demo.domain.user.mapper;

import com.example.demo.domain.user.dto.UserRequest;
import com.example.demo.domain.user.dto.UserResponse;
import com.example.demo.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring") //스프링 빈주입
public interface UserMapper {
    //User Entity -> Response DTO 변환
    UserResponse toResponse(User user);

    //User Request DTO -> Entity 변환
    User toEntity(UserRequest request);
}
