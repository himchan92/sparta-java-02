package com.sparta.java02.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDslConfig {

  @PersistenceContext
  private EntityManager entityManager;

  // Querydsl 을 사용하려면 아래 객체가 필수인데 EntityManager를 통해서 생성가능
  // 여기저기 JPAQueryFactory 생성하면 번거로우니 Config 파일로 별도 공통화
  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }
}
