package com.sparta.java02.domain.auth.controller;

import com.sparta.java02.common.exception.ServiceException;
import com.sparta.java02.common.exception.ServiceExceptionCode;
import com.sparta.java02.common.response.ApiResponse;
import com.sparta.java02.domain.auth.dto.LoginRequest;
import com.sparta.java02.domain.auth.dto.LoginResponse;
import com.sparta.java02.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.awt.print.Pageable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ApiResponse<LoginResponse> login(HttpSession session, @Valid @RequestBody LoginRequest request) {

    LoginResponse loginResponse = authService.login(request);

    //키 기준 값을 세션에 저장
    session.setAttribute("userId", loginResponse.getUserId());
    session.setAttribute("name", loginResponse.getName());
    session.setAttribute("email", loginResponse.getEmail());

    log.info("session id : {}", session.getId());

    return ApiResponse.success(authService.login(request));
  }

  //QueryString을 여러개 보낼때 파라미터로 DTO를 전달하는데 그럴경우 DTO에 @AllArgsConstructor 필수(없으면 생성자 없어서 Json 안되어 오류)
  //RequestParam은 단일객체 넘길때 사용이고 DTO 통으로 넘길땐 생략가능
//  @GetMapping("/login")
//  public ApiResponse<LoginResponse> loginQueryString(LoginRequest request1, Pageable pageable) {
//    // localhost:8080/api/auth/login?email=asb@naver.com$name=1234$email=asb@naver.com&name=1234&page=1&size=2
//
//    return ApiResponse.success();
//  }

  @GetMapping("/status")
  public ApiResponse<LoginResponse> getStatus(HttpSession session) {
    // 유저 정보 받으려면 세션이 필요
    Long userId = (Long)session.getAttribute("userId");
    String name = (String)session.getAttribute("name");
    String email = (String)session.getAttribute("email");

    if(ObjectUtils.isEmpty(userId) && ObjectUtils.isEmpty(name) && ObjectUtils.isEmpty(email)) {
      throw new ServiceException(ServiceExceptionCode.NOT_FOUND_DATA);
    }

    return ApiResponse.success(authService.getLoginResponse(userId, name, email));
  }
}