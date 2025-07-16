package com.sparta.java02.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.java02.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional //디폴트 자동롤백이므로 수행후 삭제처리되어 INSERT문 직접 확인하려면 별도 옵션 필요함
@SpringBootTest
class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private EntityManager entityManager;

  @Test
  void saveAllUser() {
    //given
    List<User> users = getUsers(2000);

    //when
    userService.saveAllUser(users);

    //then
    List<User> savedUsers = entityManager.createQuery("SELECT u FROM User u ORDER BY u.id", User.class).getResultList();

    assertThat(savedUsers).hasSize(2000);
  }

  private List<User> getUsers(int count) {
    List<User> users = new ArrayList<>();
    for(int i = 0; i < count; i++) {
      users.add(User.builder()
          .name("name" + i)
          .email("email" + i + "@gmail.com")
          .password("password" + i)
          .build());
    }

    return users;
  }
}