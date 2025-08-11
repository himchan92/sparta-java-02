package com.sparta.java02.javaReview;

interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {

    @Override
    public void send(String message) {
        System.out.println("이메일 발송 : " + message);
    }
}

class SmNotifier implements Notifier {

    @Override
    public void send(String message) {
        System.out.println("SMS발송 : " + message);
    }
}

public class JavaProgramming3 {
    public static void main(String[] args) {
        Notifier notifier;

        notifier = new EmailNotifier();
        notifier.send("회원가입 축하합니다.");

        notifier = new SmNotifier();
        notifier.send("인증번호 1234입니다.");
    }
}
