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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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

    @Transactional
    public UserResponse createUser(@Valid UserCreateRequest request) {
        //이메일중복확인
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
        }

        //비밀번호암호화
        String encodePassword = passwordEncoder.encode(request.getPassword());

        //DTO -> ENTITY 변환하여 DB저장
        User user = request.toEntity(encodePassword);
        User savedUser = userRepository.save(user);

        return UserResponse.fromEntity(savedUser);
    }
}
