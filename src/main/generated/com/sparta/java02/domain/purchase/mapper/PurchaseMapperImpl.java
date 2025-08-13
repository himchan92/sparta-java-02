package com.sparta.java02.domain.purchase.mapper;

import com.sparta.java02.domain.purchase.dto.PurchaseResponseDTO;
import com.sparta.java02.domain.purchase.entity.Purchase;
import com.sparta.java02.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-13T22:29:10+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class PurchaseMapperImpl implements PurchaseMapper {

    @Override
    public PurchaseResponseDTO toResponseDTO(Purchase purchase) {
        if ( purchase == null ) {
            return null;
        }

        PurchaseResponseDTO.PurchaseResponseDTOBuilder purchaseResponseDTO = PurchaseResponseDTO.builder();

        purchaseResponseDTO.purchaseId( purchase.getId() );
        purchaseResponseDTO.username( purchaseUserName( purchase ) );
        purchaseResponseDTO.totalPrice( purchase.getTotalPrice() );

        return purchaseResponseDTO.build();
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
