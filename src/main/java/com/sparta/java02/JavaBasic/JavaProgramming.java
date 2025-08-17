package com.sparta.java02.JavaBasic;

interface Speaker {
    void makeSound();
}

class Dog implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("멍멍");
    }
}

class Cat implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("야옹");
    }
}

public class JavaProgramming {
    public static void main(String[] args) {
        Speaker speaker = new Dog();
        speaker.makeSound();

        speaker = new Cat();
        speaker.makeSound();
    }
}
