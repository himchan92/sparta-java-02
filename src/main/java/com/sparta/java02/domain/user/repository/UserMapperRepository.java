package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapperRepository {
  User getUserById(int id);
  void insertUser(User user);
}
