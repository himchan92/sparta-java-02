package com.sparta.java02.domain.user.mapper;

import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-20T12:42:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.email( user.getEmail() );

        return userResponse.build();
    }

    @Override
    public User toEntity(UserCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( request.getName() );
        user.email( request.getEmail() );

        return user.build();
    }

    @Override
    public User toEntity(String encode) {
        if ( encode == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        return user.build();
    }
}
