package com.sparta.java02.javaReview;

class Car {
    //메소드
    void move() {
        System.out.println("자동차가 움직입니다.");
    }
}

class Student {
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if(score >= 0 && score <= 100) {
            this.score = score;
        }
        else {
            System.out.println("유효하지 않은 점수입니다.");
        }
    }
}

class Animal {
    void eat() {
        System.out.println("사료먹을시간^^");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("멍멍");
    }
}

public class JavaProgramming {
    public static void main(String[] args) {
        Car myCar = new Car();
        myCar.move();

        Car yourCar = new Car();
        yourCar.move();

        Student student = new Student();
        student.setScore(95);
        System.out.println("학생의 점수: " + student.getScore());

        student.setScore(150);
        System.out.println("학생의 점수 : " + student.getScore());
    }
}
