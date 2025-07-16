package com.sparta.java02.domain.purchase.repository;

import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  //언더바_Id : 언더바 구분이용해서 User 안에 Id를 접근한다는의미이며 id와 user_id가 있어야함(JPA 메소드)
  Optional<Purchase> findByIdAndUser_Id(Long id, Long userId);

  Long user(User user);

  @Modifying(clearAutomatically = true) //영속성 자동 클리어, UPDATE문 or DELETE문 JPQL 임을 명시
  @Query("UPDATE Purchase p SET p.status = 'COMPLETE' where p.createdAt < :date AND p.status = 'PENDING'")
  int bulkUpdateStatus(@Param("date") LocalDateTime date);
}
