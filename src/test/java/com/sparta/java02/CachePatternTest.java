package com.sparta.java02;

import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

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

    //캐시, 원본데이터 동시에 기록하여 강력한 일관성 보장
    //쓰기, 읽기 동시작업으로 1비트 차이도 허용안되는 금융, 재고, 주문처리 시스템에 적합
    //쓰기, 읽기 동시에 하다보니 쓰기 성능저하
    @Test
    public void writeThroughPattern() {
        Long updateUserId = 1L;
        String updateData = "김수현";

        log.info("Write-through: 사용자 ID {}의 데이터 저장 요청.", updateUserId);

        try {
            cacheTemplate.put(updateUserId, updateData);
            log.info("캐시에 사용자 ID {}의 데이터 저장 완료.", updateUserId);

            userRepository.put(updateUserId, updateData);
            log.info("데이터에비스에 사용자 ID {}의 데이터 저장 완료", updateUserId);

            log.info("Write-through: 사용자 ID {}의 데이터 캐시와 DB에 성공적으로 저장되었습니다.");
        } catch (Exception e) {
            log.info("Write-through 실패! 사용자ID {}의 데이터 저장 중 오류 발생: {}", updateUserId, e.getMessage());
            throw new RuntimeException("데이터 저장 실패", e);
        }
    }

    //캐시 우선저장 후 데이터는 나중에 비동기 업데이트하여 쓰기성능 극대화
    //지연 동기화 시 일시적으로 캐시와 데이터간의 불일치 발생 단점
    //짧은 시간 대량데이터 처리위한 대규모 배치처리에 적합
    @Test
    public void writeBackPattern() {
        Long updateUserId = 1L;
        String updateData = "김수현";

        log.info("Write-back: 사용자 ID {}의 데이터 저장 요청 (캐시 우선).", updateUserId);

        cacheTemplate.put(updateUserId, updateData);
        log.info("캐시에 사용자 ID {}의 데이터 저장 완료. (응답 즉시 반환)", updateUserId);

        CompletableFuture.runAsync(() -> {
            try {
                userRepository.put(updateUserId, updateData);
                log.info("비동기 DB 업데이트 완료: 사용자 ID {}의 데이터가 DB에 최종 저장되었습니다.", updateUserId);
            } catch (Exception e) {
                log.info("비동기 DB 업데이트 실패! 사용자 ID {} 의 데이터 저장 중 오류 발생: {}", updateUserId, e.getMessage());
            }
        });
    }

    //쓰기성능에 캐시나 부하영향없음
    //읽기에만 성능향상 하려는경우 적합ㄷ
    @Test
    public void writeAroundPattern() {
        Long updateUserId = 1L;
        String updateData = "김수현";

        log.info("Write-around: 사용자 ID {}의 데이터 저장 요청 (DB에만 저장).", updateUserId);

        userRepository.put(updateUserId, updateData);
        log.info("데이터베이스에 사용자 ID {}의 데이터 저장 완료.", updateUserId);

        //선택: 캐시에 오래된 데이터 존재시 삭제
        cacheTemplate.remove(updateUserId);
    }
}
