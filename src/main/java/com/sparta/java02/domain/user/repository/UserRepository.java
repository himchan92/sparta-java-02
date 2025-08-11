package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@Repository : 스프링제공 JpaRepository를 상속받아서 빈주입별도 처리안해도되서 필요없는데 코드명시상 Repository 역할명시 해주는것
public interface UserRepository extends JpaRepository<User, Long> {

    //이메일로 유저 조회(null 예외방지위해 Optional 사용)
    Optional<User> findByEmail(String email);

    //이메일기준 유저 조회 @Query 사용
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email")String email); //@Param은 파라미터명이 다르면 필수이나 같아도 명시해주는걸 권장

    //DB 고유함수나 문법 필요시 nativeQuery 사용
    //nativeQuery에서는 파라미터매핑을 1 처럼 작성하여 매핑
    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    User findByUsernameNative(String username);

    //특정날짜이후가입한 유저들 이름순으로 정렬조회
    List<User> findByCreatedAtAfterOrderByUsernameAsc(LocalDateTime dateTime);
}