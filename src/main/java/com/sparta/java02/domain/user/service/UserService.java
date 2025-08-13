package com.sparta.java02.domain.user.service;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.entity.User;
import com.sparta.java02.domain.user.mapper.UserMapper;
import com.sparta.java02.domain.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ServiceExceptionCode.NOT_FOUND_USER));
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserResponse create(@Valid UserCreateRequest request) {
        //DTO -> ENTITY 변환
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);

        //ENTITY -> DTO 변환
        return userMapper.toResponse(savedUser);
    }
}
