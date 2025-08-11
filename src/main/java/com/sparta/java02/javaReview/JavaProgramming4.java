package com.sparta.java02.javaReview;

import java.util.ArrayList;
import java.util.List;

public class JavaProgramming4 {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Apple");

        System.out.println("과일목록: " + fruits);
        System.out.println("과일목록: " + fruits.get(0));
    }
}
