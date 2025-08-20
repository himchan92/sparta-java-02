package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository //JpaRepository 상속 시 없어도 동작이상없으나, 역할 명시와 예외 변환을 위해 명시적으로 붙임
public interface UserRepository extends JpaRepository<User, Long> {

  //이메일 기준 유저조회(없을수도 있어 Optional)
  //파라미터명이 일치하면 생략해도 정상동작되나 실무에서는 명시권장
  @Query("select u from User u where u.email = :email")
  Optional<User> findUserByEmail(@Param("email") String email);

  //특정 날짜이후 가입유저들 이름순으로 정렬조회
  //메소드명 너무길면 가독성안좋아서 비추
  List<User> findByCreatedAtAfterOrderByNameAsc(LocalDateTime dateTime);

  //이메일중복체크
  Optional<Object> findByEmail(String email);
}