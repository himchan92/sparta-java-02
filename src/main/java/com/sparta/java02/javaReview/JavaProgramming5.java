package com.sparta.java02.javaReview;

import java.util.HashSet;
import java.util.Set;

public class JavaProgramming5 {
    public static void main(String[] args) {
        Set<String> unique = new HashSet<>();
        unique.add("Alice");
        unique.add("Bob");
        unique.add("Alice"); //Set은 중복 제거효과

        System.out.println("고유이름: " + unique);
        System.out.println("고유이름 Bob있나 : " + unique.contains("Bob"));
    }
}
