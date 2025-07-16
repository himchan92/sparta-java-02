package com.sparta.java02.domain.product.repository;

import com.sparta.java02.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//@Repository : 아래와 같이 지원된것 상속받는 경우 스프링부트내부 자동빈주입되어 생략가능, 단 수동으로 직접만든경우 붙여줘야함
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Modifying(clearAutomatically = true) //쿼리 수행 후 영속성 자동 클리어, DML 임을 명시
  @Query("UPDATE Product p SET p.price = p.price * 0.9 WHERE p.category = :category")
  int applyDiscountByCategory(@Param("category") String category);
}
