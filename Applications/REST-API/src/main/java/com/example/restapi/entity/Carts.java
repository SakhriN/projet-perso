package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Carts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cartsId;

    private Double cartsTotalAmount;


    @OneToOne
    @JoinColumn(name = "sessions_id")
    private Sessions sessions;

    @JsonIgnore
    @OneToMany(mappedBy = "carts")
    private List<CartItems> cartItemsList;

    @Override
    public String toString() {
        return "Carts{" +
                "cartsId=" + cartsId +
                ", cartsTotalAmount=" + cartsTotalAmount +
                '}';
    }
}
