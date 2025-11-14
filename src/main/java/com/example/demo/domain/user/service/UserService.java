package com.example.demo.domain.user.service;

import com.example.demo.domain.user.dto.UserCreateRequest;
import com.example.demo.domain.user.dto.UserResponse;
import com.example.demo.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(@Valid UserCreateRequest request) {
        return null;
    }
}