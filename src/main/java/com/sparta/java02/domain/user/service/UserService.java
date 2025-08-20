package com.sparta.java02.domain.user.service;

import com.sparta.java02.common.enums.ServiceExceptionCode;
import com.sparta.java02.common.exception.ServiceException;
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

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse create(UserCreateRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public UserResponse createUser(@Valid UserCreateRequest request) {
        //이메일 중복확인
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ServiceException(ServiceExceptionCode.DUPLICATE_EMAIL);
        }

        //비밀번호 암호화
        String encode = passwordEncoder.encode(request.getPassword());

        //ENTITY로변환하여 DB저장
        User user = userMapper.toEntity(encode);
        User savedUser = userRepository.save(user);

        return UserResponse.fromEntity(savedUser);
    }

    @Transactional
    public void updateUser(Long id, String newName) {
        User user = userRepository.findById(id).get();

        user.changeName(newName);
        //save없이 setter, Transactional 만으로 변경감지일어나 update 수행지원
    }
}
