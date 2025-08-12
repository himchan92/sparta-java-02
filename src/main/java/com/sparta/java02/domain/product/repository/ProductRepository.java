package com.sparta.java02.domain.product.repository;

import com.sparta.java02.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //스프링빈등록된 제공객체(JpaRepository) 상속받아서 없어도되지만 역할명시를 위해 작성
public interface ProductRepository extends JpaRepository<Product, Long> {

}
