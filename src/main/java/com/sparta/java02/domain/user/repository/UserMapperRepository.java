package com.sparta.java02.domain.user.repository;

import com.sparta.java02.domain.user.dto.UserDTO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapperRepository {

  Map<String, Object> selectUserById(@Param("id") Long id);

  void insertUser(@Param("users")List<UserDTO> users);
}
