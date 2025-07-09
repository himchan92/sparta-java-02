package com.sparta.java02;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
public class RedisDataTest {

  private static final Logger log = LoggerFactory.getLogger(RedisDataTest.class);

  @Autowired
  private Jedis jedis;

  @Test
  void redisStringExample() {
    // api : /api/users/34 API 호출
    jedis.set("user:55:session", "{\"id\" : 34, \"name\" : \"홍길동\"}");
    jedis.expire("users:55:session", 3600);

    String response = jedis.get("users:55:session");
    log.info("/api/users/34 요청에 따른 캐싱 된 응답 값 : {}", response);

    Long ttl = jedis.ttl("users:55:session");
    log.info("ttl : {}", ttl);
  }

  @Test
  void redisListExample() {

    jedis.del("queue:task");

    //왼쪽에서부터 task1~5 넣고 빼는건 마지막에 넣은 5를 먼저 뺀다.
    jedis.lpush("queue:task", "task1", "task2", "task3", "task4", "task5");

    jedis.rpush("queue:task", "task1", "task2", "task3", "task4", "task5");

    Long queueSize = jedis.llen("queue:task");
    log.info("queueSize : {}", queueSize);

    String L_task = jedis.lpop("queue:tasks");
    log.info("L_task : {}", L_task);

    String R_task = jedis.rpop("queue:tasks");
    log.info("R_task : {}", R_task);
  }

  @Test
  void redisHashExample() {
    jedis.hset("use:123", "name", "John Doe");
    jedis.hset("use:123", "email", "john.doe@example.com");
    jedis.hset("use:123", "age", "30");
    jedis.hset("use:123", "city", "New York");

    String name = jedis.hget("user:123", "name");

    Map<String, String> hget = jedis.hgetAll("user:123");
    log.info("java map : {}, redis : {}", hget.get("name"), name);
  }

  @Test
  void redisSortedSetExample() {
    jedis.zadd("user:123:friendly", 100, "friend1");
    jedis.zadd("user:123:friendly", 200, "friend2");
    jedis.zadd("user:123:friendly", 300, "friend3");
    jedis.zadd("user:123:friendly", 400, "friend4");

    List<String> zrevrangeFriends = jedis.zrevrange("user:123:friendly", 0, 2); //오름차순
    List<String> zrangeFriends = jedis.zrange("user:123:friendly", 0, 2); //내름차순
    log.info("friends : {}, {}", zrevrangeFriends, zrangeFriends);
  }
}
