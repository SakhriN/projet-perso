package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Carts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID cartsId;

    private Double cartsTotalAmount;


    @OneToOne
    @JoinColumn(name = "sessions_id")
    private Sessions sessions;

    @OneToMany(mappedBy = "carts", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItems> cartItemsList;

    @Override
    public String toString() {
        return "Carts{" +
                "cartsId=" + cartsId +
                ", cartsTotalAmount=" + cartsTotalAmount +
                '}';
    }
}
