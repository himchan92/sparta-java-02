package com.sparta.java02.global.config;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class RedisTest {

  //테스트코드에서는 Slf4j가 안되어 직접 Logger 가져와 로그 찍기
  private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

  @Autowired //스프링컨테이너한테 대신 객체생성해달라고 요청(DI)
  private Jedis jedis;

  @Test
  void testJedis() {
    jedis.set("key", "Hello Jedis!!");
    String value = jedis.get("key");

    log.info("value : {}", value);
  }
}