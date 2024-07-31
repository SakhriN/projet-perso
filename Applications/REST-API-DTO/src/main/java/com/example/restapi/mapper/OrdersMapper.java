package com.example.restapi.mapper;

import com.example.restapi.dto.OrdersDTO;
import com.example.restapi.entity.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

        @Mapping(source = "usersId", target = "users.usersId")
        Orders toOrders(OrdersDTO ordersDTO);

        @Mapping(source = "users.usersId", target = "usersId")
        OrdersDTO toOrdersDto(Orders orders);
        }
