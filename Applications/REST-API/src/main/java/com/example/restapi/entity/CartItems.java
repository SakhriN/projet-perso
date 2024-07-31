package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartItemsId;

    private Long cartItemsQuantity;

    private Double cartItemsPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carts_id")
    private Carts carts;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;
}
