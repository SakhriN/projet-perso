package com.example.restapi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentsDTO {

    @Id
    private UUID paymentsId;

    private Date paymentsDate;

    private String paymentsMethod;

    private Double paymentsAmount;

    private Long paymentsTransactionId;

    private Boolean paymentsStatus;

    private UUID ordersId;
}
