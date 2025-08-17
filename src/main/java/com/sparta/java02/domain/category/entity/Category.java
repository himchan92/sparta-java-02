package com.sparta.java02.domain.category.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "category")
@DynamicUpdate
@DynamicInsert
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //protected로 외부에서 사용 못 하게 막고, JPA 내부 동작만 허용
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  //다대다는 실무비추로 일대다~다대일분리 권장
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id") //부모
  Category parent; //부모카테고리

  @JsonBackReference
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
  List<Category> children = new ArrayList<>(); //자식목록
}
