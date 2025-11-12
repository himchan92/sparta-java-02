package com.example.demo.domain.user.repository;

import com.example.demo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // TODO: 이메일기준 유저조회(없을수도 있으니 Optional)
    Optional<User> findByEmail(String email);

    // TODO: 특정 날짜 이후에 가입한 유저들을 이름 순으로 정렬하여 조회
    List<User> findByCreatedAtAfterOrderByUsernameAsc(LocalDateTime dateTime);

    // TODO: @Query 방식, u alias문 필수, 파라미터 : 매핑필수
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
}