package com.sparta.java02.domain.category.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.java02.domain.category.dto.CategoryResponse;
import com.sparta.java02.domain.category.entity.Category;
import com.sparta.java02.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final Jedis jedis;
    private final ObjectMapper objectMapper;

    private final CategoryRepository categoryRepository;

    private static final String CACHE_KEY_CATEGORY_STRUT = "categoryStruct";
    private static final int CACHE_EXPIRE_SECONDS = 3600; //1시간

    private List<CategoryResponse> findCategoryStruct() {
        List<Category> categories = categoryRepository.findAll();

        Map<Long, CategoryResponse> categoryResponseMap = new HashMap<>();

        for(Category category : categories) {
            CategoryResponse response = CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .categories(new ArrayList<>())
                    .build();
            categoryResponseMap.put(category.getId(), response);
        }

        List<CategoryResponse> rootCategories = new ArrayList<>();
        for(Category category : categories) {
            CategoryResponse categoryResponse = categoryResponseMap.get(category.getId());

            //부모없음 = 최상위
            if(ObjectUtils.isEmpty(category.getParent())) {
                rootCategories.add(categoryResponse);
            }
            else {
                CategoryResponse parentResponse = categoryResponseMap.get(category.getParent().getId());
                if(parentResponse != null) {
                    parentResponse.getCategories().add(categoryResponse);
                }
            }
        }

        return rootCategories;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findCategoryStructCacheAside() throws JsonProcessingException {
        //캐시에서 데이터조회
        String cachedCategories = jedis.get(CACHE_KEY_CATEGORY_STRUT);

        //캐시 해당
        if(!ObjectUtils.isEmpty(cachedCategories)) {
            log.info("Cache Hit: categoryStruct for key " + CACHE_KEY_CATEGORY_STRUT);
            return objectMapper.readValue(cachedCategories, new TypeReference<>() {});
        }

        //캐시미스 DB에서 조회
        log.info("Cache Miss: categoryStruct for key " + CACHE_KEY_CATEGORY_STRUT);
        List<CategoryResponse> rootCategories = findCategoryStruct();

        //DB 조회 데이터를 캐시에 저장
        if(!ObjectUtils.isEmpty(rootCategories)) {
            String jsonString = objectMapper.writeValueAsString(rootCategories);
            jedis.setex(CACHE_KEY_CATEGORY_STRUT, CACHE_EXPIRE_SECONDS, jsonString);
        }

        return rootCategories;
    }
}
