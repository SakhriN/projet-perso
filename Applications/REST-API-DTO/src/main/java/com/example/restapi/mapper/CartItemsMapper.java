package com.example.restapi.mapper;

import com.example.restapi.dto.CartItemsDTO;
import com.example.restapi.entity.CartItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemsMapper {
        @Mapping(source = "productsId", target = "products.productsId")
        @Mapping(source = "cartsId", target = "carts.cartsId")
        CartItems toCartItems(CartItemsDTO cartItemsDTO);
        @Mapping(source = "products.productsId", target = "productsId")
        @Mapping(source = "carts.cartsId", target = "cartsId")
        CartItemsDTO toCartItemsDto(CartItems cartItems);
}
