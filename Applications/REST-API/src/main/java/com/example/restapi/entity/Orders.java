package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ordersId;

    private Date ordersDate;

    private OrderStatus ordersStatus;

    private Double ordersTotalAmount;

    private String orders_client_address;

    private String orders_shipping_address;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @JsonIgnore
    @OneToOne(mappedBy = "orders")
    private Payments payments;

    @JsonIgnore
    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItemsList;

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", ordersDate=" + ordersDate +
                ", ordersStatus=" + ordersStatus +
                ", ordersTotalAmount=" + ordersTotalAmount +
                ", orders_client_address='" + orders_client_address + '\'' +
                ", orders_shipping_address='" + orders_shipping_address + '\'' +
                '}';
    }
}
