package com.sparta.java02.domain.purchase.mapper;

import com.sparta.java02.domain.purchase.dto.PurchaseResponse;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T22:46:51+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PurchaseMapperImpl implements PurchaseMapper {

    @Override
    public PurchaseResponse toResponseDTO(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }

        PurchaseResponse.PurchaseResponseBuilder purchaseResponse = PurchaseResponse.builder();

        purchaseResponse.purchaseId( purchase.getId() );
        purchaseResponse.username( purchaseUserName( purchase ) );
        purchaseResponse.totalPrice( purchase.getTotalPrice() );

        return purchaseResponse.build();
    }

    private String purchaseUserName(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }
        User user = purchase.getUser();
        if ( user == null ) {
            return null;
        }
        String name = user.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
