package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ordersId;

    private Date ordersDate;

    private OrderStatus ordersStatus;

    private Double ordersTotalAmount;

    private String ordersClientAddress;

    private String ordersShippingAddress;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToOne(mappedBy = "orders")
    private Payments payments;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItemsList;

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", ordersDate=" + ordersDate +
                ", ordersStatus=" + ordersStatus +
                ", ordersTotalAmount=" + ordersTotalAmount +
                ", orders_client_address='" + ordersClientAddress + '\'' +
                ", orders_shipping_address='" + ordersShippingAddress + '\'' +
                '}';
    }
}
