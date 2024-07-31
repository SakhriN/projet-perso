package com.example.restapi.mapper;

import com.example.restapi.dto.OrderItemsDTO;
import com.example.restapi.entity.OrderItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemsMapper {

        @Mapping(source = "productsId", target = "products.productsId")
        @Mapping(source = "ordersId", target = "orders.ordersId")
        OrderItems toOrderItems(OrderItemsDTO orderItemsDTO);

        @Mapping(source = "products.productsId", target = "productsId")
        @Mapping(source = "orders.ordersId", target ="ordersId")
        OrderItemsDTO toOrderItemsDto(OrderItems orderItems);
        }
