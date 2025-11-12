package com.example.demo.domain.purchase.entity;

import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 여러개주문(N) : 한사용자(1)
    @JoinColumn(name = "user_id") // 매핑테이블 FK 명시
    private User user;
}
