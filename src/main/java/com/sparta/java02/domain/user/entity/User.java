package com.sparta.java02.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity //JPA관리임을 명시
@Getter
@DynamicInsert //null 아닌값만 INSERT
@DynamicUpdate //변경내용만 UPDATE
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA는 기본생성자 필수
@Table(name = "user") //실제 DB테이블명
@FieldDefaults(level = AccessLevel.PRIVATE) //모든필드 접근제어자일괄설정
public class User {

    @Id //PK명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성을 DB auto increment 명시
    Long id;

    //유저(1) : 주문목록(N) 매핑

    @Column(name = "username", nullable = false, length = 50)
    String username;

    @Column(nullable = false, unique = true)
    String email;

    @Column(name = "password_hash", nullable = false)
    String passwordHash;

    @CreationTimestamp //시간자동설정
    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp //시간자동설정
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Builder //외부노출방지를 위해 필요한 필드만 보여줄경우 생성자에 Builder 명시
    public User(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
