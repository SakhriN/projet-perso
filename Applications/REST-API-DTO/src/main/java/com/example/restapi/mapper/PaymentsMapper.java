package com.example.restapi.mapper;

import com.example.restapi.dto.PaymentsDTO;
import com.example.restapi.entity.Payments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {
        @Mapping(source = "ordersId", target = "orders.ordersId")
        Payments toPayments(PaymentsDTO paymentsDTO);

        @Mapping(source = "orders.ordersId", target = "ordersId")
        PaymentsDTO toPaymentsDto(Payments payments);
        }
