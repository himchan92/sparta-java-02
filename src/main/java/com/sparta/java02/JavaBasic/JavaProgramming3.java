package com.sparta.java02.JavaBasic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaProgramming3 {
    public static void main(String[] args) {
        //중복제거효과
        Set<String> uniqueNames = new HashSet<>();
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Alice");

        //순서보장x
        System.out.println("고유한 이름을 " + uniqueNames);

        //포함여부
        System.out.println("고유한 이름을 " + uniqueNames.contains("Bob"));
    }
}
