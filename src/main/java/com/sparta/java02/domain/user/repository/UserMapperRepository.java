package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.dto.UserDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapperRepository {
  void insertUser(@Param("users")List<UserDTO> users);
}
