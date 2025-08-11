package com.sparta.java02.javaReview;

interface Speaker {
    void makeSound();
}

class Poodle implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("멍멍!");
    }
}

class Cat implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("야옹");
    }
}

public class JavaProgramming2 {
    public static void main(String[] args) {
        //인터페이스에 의존

        //푸들주입
        Speaker pet = new Poodle();
        pet.makeSound();

        //Cat주입
        pet = new Cat();
        pet.makeSound();
    }
}
