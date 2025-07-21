package com.sparta.java02.domain.category.repository;

import com.sparta.java02.domain.category.entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.commands.ConfigCommands;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryJdbcRepository {

  private final DataSource dataSource; //yml datasource
  private final ConfigCommands configCommands;

  public Category save(Category category) throws SQLException {
    String query = "INSERT INTO category (name) VALUES (?)";

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = dataSource.getConnection();

      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, category.getName());
      preparedStatement.executeUpdate();

      return category;
    } catch(SQLException error) {
      log.error(error.getMessage(), error);
      throw error;
    } finally {
       close(connection, preparedStatement, null);
    }
  }

  public Category findById(Connection connection, Category category) throws SQLException {
    String query = "SELECT * FROM category WHERE id = ?";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setLong(1, category.getId());
      resultSet = preparedStatement.executeQuery();
      if(resultSet.next()) {
        return Category.builder()
            .name(resultSet.getString("name"))
            .build();
      } else {
        throw new NoSuchElementException("Category not found");
      }
    } catch (SQLException error) {
      log.error(error.getMessage(), error);
      throw error;
    } finally {
      close(null, preparedStatement, resultSet);
    }
  }

  public void update(Connection connection, Long categoryId, String name) throws SQLException {
    String query = "UPDATE category SET name = ? WHERE id = ?";

    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, name);
      preparedStatement.setLong(2, categoryId);
      preparedStatement.executeUpdate();
    } catch (SQLException error) {
      log.error(error.getMessage(), error);
      throw error;
    } finally {
      close(null, preparedStatement, null);
    }
  }

  private void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
    JdbcUtils.closeResultSet(resultSet);
    JdbcUtils.closeStatement(preparedStatement);
    JdbcUtils.closeConnection(connection);
  }

  private Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
