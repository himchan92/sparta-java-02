package com.sparta.java02.global.config;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration //스프링컨테이너에게 내부적으로 Bean 있다고 알려줌
public class RedisConfig {

  @Value("${spring.data.redis.host}") //application.yml 데이터 참조 기능
  private String redisHost;

  @Value("${spring.data.redis.port}") //application.yml 데이터 참조 기능
  private int redisPort;

  @Value("${spring.data.redis.password:}") //application.yml 데이터 참조 기능, : 끝에 붙인건 password가 yml에 값이 없어서임
  private String redisPassword;

  @Bean //new Jedis 객체를 스프링컨테이너에게 DI 해달라고 알려달라고
  public Jedis jedis() {
    Jedis jedis = new Jedis(redisHost, redisPort);

    //password 값이 있으면 실행
    if(!ObjectUtils.isEmpty(redisPassword)) {
      jedis.auth(redisPassword);
    }

    return jedis;
  }
}