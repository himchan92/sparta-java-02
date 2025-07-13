package com.sparta.java02.domain.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.java02.domain.purchase.entity.QPurchase;
import com.sparta.java02.domain.user.dto.QUserPurchaseResponse;
import com.sparta.java02.domain.user.dto.UserPurchaseResponse;
import com.sparta.java02.domain.user.entity.QUser;
import com.sparta.java02.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.sparta.java02.domain.purchase.entity.QPurchase.purchase;
import static com.sparta.java02.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {

    //config 패키지에 @Bean 설정하여 생성했기에 DI 생성 가능
    private final JPAQueryFactory jpaQueryFactory;

    public Page<UserPurchaseResponse> findUsers(String name, String email, Pageable pageable) {
        //다건이니 List에 fetch()고 단건이면 User 자료형에 fetchOne() 해주면 된다.
        List<UserPurchaseResponse> users = jpaQueryFactory
                // select(QUser.user) 대신 DTO Response 활용
                // 사전 해당 DTO에 @QueryProjection 생성자 필수
                // 그냥 엔티티 사용시 타 엔티티값 가져오려면 일일이 참조하고 로직이 들어가니 DTO로 별도 만들어 활용하는것
                .select(new QUserPurchaseResponse(
                        user.id,
                        user.name,
                        user.email,
                        purchase.id,
                        purchase.totalPrice
                        //purchase.totalPrice.as("price") // 서로 필드명이 다른경우 as 써서 매칭도 가능
                ))
                //.selectFrom(user) -> select from 한번에 호출도되나 분리해서 많이 작성
                //.select(user)
                .from(user)
                // SQL 조인 지원
                // Purchase 엔티이에 User id 있어서 join 가능
                //.join(purchase).on(user.id.eq(purchase.user.id))
                // id 까지 명시안해도 알아서 내부적으로 id 끼리 조인처리해줌
                .join(purchase).on(user.eq(purchase.user))

                //pk id 에 and 연산 user name 홍길동인것 조인
                //.join(purchase).on(user.eq(purchase.user), user.name.eq("홍길동"))
                .where(
                        // querydsl 제공하는 eq 연산자 사용
                        //콤마구분은 자동 and 연산으로 둘다 만족
                        // or 조건은 직접 ,(콤마)대신 or 써서 작성
                        user.name.eq(name),
                        user.email.eq(email),
                        //contains : SQL LIKE 연산자 %% 조건
                        //값 null 체크위해 hasText 사용
                        StringUtils.hasText(name) ? user.name.contains(name) : null
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch(); //데이터 여러개조회는 fetch()로 종결(한건은 fetchOne())

        //총건수 조회
        Long totalCount = jpaQueryFactory.select(user.count())
                .from(user)
                .join(purchase).on(user.eq(purchase.user))
                .where(
                        nameContains(name),
                        emailContains(email)
                ).fetchOne(); //단건 조회이니 fetchOne() 사용

        // Pageable은 PageImpl로 반환
        return new PageImpl<>(users, pageable, totalCount);
    }

    // BooleanExpression : return 값이 true or false 인지만 판별
    private BooleanExpression nameContains(String name) {
        return StringUtils.hasText(name) ? user.name.contains(name) : null;
    }

    private BooleanExpression emailContains(String email) {
        return StringUtils.hasText(email) ? user.name.contains(email) : null;
    }
}
