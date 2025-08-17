package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //JpaRepository 상속 시 없어도 동작이상없으나, 역할 명시와 예외 변환을 위해 명시적으로 붙임
public interface UserRepository extends JpaRepository<User, Long> {
}
