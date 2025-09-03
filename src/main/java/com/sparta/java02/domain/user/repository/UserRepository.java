package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository //JpaRepository 상속받아서 스프링 Data Jpa가 자동 빈등록되어 생략가능
public interface UserRepository extends JpaRepository<User, Long> {

    //없을수도 있으니 Optional 사용
    Optional<User> findByEmail(String email);

    //특정날짜이후 가입유저들 이름순으로 정렬조회
    List<User> findByCreatedAtAfterOrderByUsernameAsc(LocalDateTime dateTime);

    //메소드로 한계있을시 쿼리문이용하여 설정가능
    //테이블명아닌 엔티티명으로 사용
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
}
