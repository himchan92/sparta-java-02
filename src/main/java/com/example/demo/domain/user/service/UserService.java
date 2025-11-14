package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.UserRequest;
import com.example.demo.domain.user.dto.UserResponse;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.mapper.UserMapper;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper; // 생성자 주입

    @Transactional
    public UserResponse create(UserRequest request) {
        // DTO -> Entity 변환
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        // Entity -> DTO 변환
        return userMapper.toResponse(savedUser);
    }
}