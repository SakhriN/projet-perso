package com.example.restapi.dto;

import com.example.restapi.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ordersId;
    private Date ordersDate;
    private OrderStatus ordersStatus;
    private Double ordersTotalAmount;
    private String ordersClientAddress;
    private String ordersShippingAddress;
    private UUID usersId;

}
