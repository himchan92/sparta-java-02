package com.sparta.java02;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@Slf4j
@SpringBootTest
public class CachePatternTest {

    private static final Logger log = LoggerFactory.getLogger(CachePatternTest.class);

    private HashMap<Long, String> cacheTemplate = new HashMap<>();
    private HashMap<Long, String> userRepository = new HashMap<>();

    //테스트 종료 후 수행
    @BeforeEach
    void setUp() {
        userRepository.put(1L, "김철수");
        userRepository.put(2L, "최민수");
    }

    @Test
    public void cacheAsidePattern() {
        Long searchUserId = 1L;

        String cachedData = cacheTemplate.get(searchUserId);

        if(cachedData != null) {
            log.info("캐시에서 온 데이터 : {}", cachedData);
            return;
        }

        log.info("사용자 ID {}의 데이터를 데이터베이스에서 조회합니다.", searchUserId);
        String dbData = userRepository.get(searchUserId);

        if(dbData != null) {
            cacheTemplate.put(searchUserId, dbData);
            log.info("사용자 ID {}의 데이터를 캐시에 저장했습니다.", searchUserId);
        } else {
            log.info("사용자 ID {}의 데이터가 데이터베이스에 존재하지 않습니다.", searchUserId);
        }
    }

}
