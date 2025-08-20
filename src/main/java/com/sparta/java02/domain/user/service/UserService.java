package com.sparta.java02.domain.user.service;

import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.mapper.UserMapper;
import com.sparta.java02.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse create(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}
