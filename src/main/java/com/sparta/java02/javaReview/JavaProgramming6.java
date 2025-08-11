package com.sparta.java02.javaReview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaProgramming6 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Alice", 26);

        System.out.println("사용자 나이정보: " + map);
        System.out.println("사용자 나이정보: " + map.get("Bob"));
        System.out.println();

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        for(String name : names) {
            System.out.println("이름: " + name);
        }
    }
}
