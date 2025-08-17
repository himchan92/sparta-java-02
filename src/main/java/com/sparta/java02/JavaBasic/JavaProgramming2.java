package com.sparta.java02.JavaBasic;

import java.util.ArrayList;
import java.util.List;

public class JavaProgramming2 {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple");

        //순서보장
        System.out.println("과일 목록: " + fruits);
        System.out.println("과일 목록: " + fruits.get(0));
    }
}
