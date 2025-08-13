package com.sparta.java02.domain.user.controller;

import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.user.dto.UserCreateRequest;
import com.sparta.java02.domain.user.dto.UserResponse;
import com.sparta.java02.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //validation gradle에 @Valid 효과로 DTO 필드검증
    //컨트롤러단 예외는 global exception 에서 모두 받아 처리
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //성공시 응답코드 201 설정
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest request) { //Request 필드검증하고 실패시 GlobalExceptionHandler가 자동실패응답처리
        //파라미터 DTO 검증통과하면 서비스단 수행
        return ApiResponse.success(userService.createUser(request));
    }
}
