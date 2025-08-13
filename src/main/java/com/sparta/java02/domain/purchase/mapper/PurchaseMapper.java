package com.sparta.java02.domain.purchase.mapper;

import com.sparta.java02.domain.purchase.dto.PurchaseResponseDTO;
import com.sparta.java02.domain.purchase.entity.Purchase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    //@Mapping: 타켓과 대상간의 필드명이 다른경우 매핑
    @Mapping(source = "id", target = "purchaseId")
    @Mapping(source = "user.name", target = "username")
    PurchaseResponseDTO toResponseDTO(Purchase purchase);
}
