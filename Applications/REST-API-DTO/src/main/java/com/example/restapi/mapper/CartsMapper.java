package com.example.restapi.mapper;

import com.example.restapi.dto.CartsDTO;
import com.example.restapi.entity.Carts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartsMapper {
    @Mapping(source = "sessionsId", target = "sessions.sessionsId")
    Carts toCarts(CartsDTO cartsDTO);

    @Mapping(source = "sessions.sessionsId", target = "sessionsId")
    CartsDTO toCartsDto(Carts carts);
}
