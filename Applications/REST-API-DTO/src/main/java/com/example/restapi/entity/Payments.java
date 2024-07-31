package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentsId;

    private Date paymentsDate;

    private String paymentsMethod;

    private Double paymentsAmount;

    private Long paymentsTransactionId;

    private Boolean paymentsStatus;

    @OneToOne
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Override
    public String toString() {
        return "Payments{" +
                "paymentsId=" + paymentsId +
                ", paymentsDate=" + paymentsDate +
                ", paymentsMethod='" + paymentsMethod + '\'' +
                ", paymentsAmount=" + paymentsAmount +
                ", paymentsTransactionId=" + paymentsTransactionId +
                ", paymentsStatus=" + paymentsStatus +
                '}';
    }
}
