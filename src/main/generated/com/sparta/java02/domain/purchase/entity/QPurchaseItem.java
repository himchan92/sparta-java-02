package com.sparta.java02.domain.purchase.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseItem is a Querydsl query type for PurchaseItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseItem extends EntityPathBase<PurchaseItem> {

    private static final long serialVersionUID = 1656446360L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseItem purchaseItem = new QPurchaseItem("purchaseItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> price = createNumber("price", java.math.BigDecimal.class);

    public final com.sparta.java02.domain.product.entity.QProduct product;

    public final QPurchase purchase;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QPurchaseItem(String variable) {
        this(PurchaseItem.class, forVariable(variable), INITS);
    }

    public QPurchaseItem(Path<? extends PurchaseItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseItem(PathMetadata metadata, PathInits inits) {
        this(PurchaseItem.class, metadata, inits);
    }

    public QPurchaseItem(Class<? extends PurchaseItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.sparta.java02.domain.product.entity.QProduct(forProperty("product"), inits.get("product")) : null;
        this.purchase = inits.isInitialized("purchase") ? new QPurchase(forProperty("purchase"), inits.get("purchase")) : null;
    }

}

