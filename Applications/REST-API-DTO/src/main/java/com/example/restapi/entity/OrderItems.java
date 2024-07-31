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
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderItemsId;

    private Long orderItemsQuantity;

    private Double orderItemsPrice;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderItemsId=" + orderItemsId +
                ", orderItemsQuantity=" + orderItemsQuantity +
                ", orderItemsPrice=" + orderItemsPrice +
                '}';
    }
}
