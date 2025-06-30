package com.sparta.java02.domain.purchase.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchase is a Querydsl query type for Purchase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchase extends EntityPathBase<Purchase> {

    private static final long serialVersionUID = 2096195813L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchase purchase = new QPurchase("purchase");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.sparta.java02.common.enums.PurchaseStatus> status = createEnum("status", com.sparta.java02.common.enums.PurchaseStatus.class);

    public final NumberPath<java.math.BigDecimal> totalPrice = createNumber("totalPrice", java.math.BigDecimal.class);

    public final com.sparta.java02.domain.user.entity.QUser user;

    public QPurchase(String variable) {
        this(Purchase.class, forVariable(variable), INITS);
    }

    public QPurchase(Path<? extends Purchase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchase(PathMetadata metadata, PathInits inits) {
        this(Purchase.class, metadata, inits);
    }

    public QPurchase(Class<? extends Purchase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.sparta.java02.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

