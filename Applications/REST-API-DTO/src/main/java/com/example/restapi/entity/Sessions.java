package com.example.restapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Sessions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sessionsId;

    private Date sessionsCreationDate;

    private Date sessionsExpireDate;

    @OneToOne(mappedBy = "sessions", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carts carts;

    @OneToOne
    @JoinColumn(name = "users_id", nullable = true)
    private Users users;


    @Override
    public String toString() {
        return "Sessions{" +
                "sessionsId=" + sessionsId +
                ", sessionsCreationDate=" + sessionsCreationDate +
                ", sessionsExpireDate=" + sessionsExpireDate +
                '}';
    }
}
