package com.example.restapi.dto;

import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItemsDTO {

    private UUID cartItemsId;
    private Long cartItemsQuantity;
    private Double cartItemsPrice;
    private UUID cartsId;
    private UUID productsId;
}
