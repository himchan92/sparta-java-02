package com.sparta.java02.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

//COMMON - 공통코드는 UTIL 패키지로 만들어 개발한다.
@Service
@RequiredArgsConstructor
public class JedisUtil {

  private final Jedis jedis;
  private final ObjectMapper objectMapper;
}
