package com.sparta.java02.domain.category.service;

import com.sparta.java02.domain.category.entity.Category;
import com.sparta.java02.domain.category.repository.CategoryJdbcRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j //로그
@Service
@RequiredArgsConstructor
public class CategoryJdbcService {

  private final DataSource dataSource; // yml > datasource 연동
  private final CategoryJdbcRepository categoryJdbcRepository;

  public void updateCategory(Long categoryId, String name) throws SQLException {
    Connection connection = dataSource.getConnection();

    try {
      connection.setAutoCommit(false); // auto commit false

      Category category = categoryJdbcRepository.findById(connection, categoryId);
      if(Objects.nonNull(category)) {
        categoryJdbcRepository.update(connection, categoryId, name);
      }

      connection.commit();
    } catch (Exception error) {
      connection.rollback();
      throw new IllegalStateException(error);
    } finally {
      release(connection);
    }
  }

  private void release(Connection connection) {
    if(connection != null) {
      try {
        connection.setAutoCommit(true);
        connection.close();
      } catch (Exception error) {
        log.info("release : ", error);
      }
    }
  }
}
