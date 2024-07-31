package com.example.restapi.dto;

import com.example.restapi.entity.CartItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartsDTO {
    private UUID cartsId;
    private Double cartsTotalAmount;
    private UUID sessionsId;
}
