package com.example.restapi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemsDTO {

    @Id
    private UUID orderItemsId;
    private Long orderItemsQuantity;
    private Double orderItemsPrice;
    private UUID ordersId;
    private UUID productsId;
}
