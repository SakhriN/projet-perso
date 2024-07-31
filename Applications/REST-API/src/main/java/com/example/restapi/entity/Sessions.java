package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Sessions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sessionsId;

    private Date sessionsCreationDate;

    private Date sessionsExpireDate;

    @JsonIgnore
    @OneToOne(mappedBy = "sessions")
    private Carts carts;

    public Sessions(){
        this.sessionsCreationDate = Date.valueOf(LocalDate.now());
        this.sessionsExpireDate = Date.valueOf(LocalDate.now().plusDays(1));
    }


    @Override
    public String toString() {
        return "Sessions{" +
                "sessionsId=" + sessionsId +
                ", sessionsCreationDate=" + sessionsCreationDate +
                ", sessionsExpireDate=" + sessionsExpireDate +
                '}';
    }
}
