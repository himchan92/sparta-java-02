package com.sparta.java02.JavaBasic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JavaProgramming4 {
    public static void main(String[] args) {
        //키-값 구조로 JSON 형태 반환에 유리
        Map<String, Integer> userAges = new HashMap<>();
        userAges.put("Alice", 25);
        userAges.put("Bob", 30);
        userAges.put("Alice", 30);

        System.out.println(userAges);
        System.out.println(userAges.get("Bob"));
    }
}
