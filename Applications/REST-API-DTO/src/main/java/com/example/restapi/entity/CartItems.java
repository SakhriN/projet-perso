package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartItemsId;

    private Long cartItemsQuantity;

    private Double cartItemsPrice;

    @ManyToOne
    @JoinColumn(name = "carts_id")
    private Carts carts;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    @Override
    public String toString() {
        return "CartItems{" +
                "cartItemsId=" + cartItemsId +
                ", cartItemsQuantity=" + cartItemsQuantity +
                ", cartItemsPrice=" + cartItemsPrice +
                '}';
    }
}
