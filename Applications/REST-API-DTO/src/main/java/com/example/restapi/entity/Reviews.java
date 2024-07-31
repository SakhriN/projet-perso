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
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID reviewsId;

    private Double reviewsRating;

    private String reviewsComment;

    private Date reviewsCreationDate;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    @Override
    public String toString() {
        return "Reviews{" +
                "reviewsId=" + reviewsId +
                ", reviewsRating=" + reviewsRating +
                ", reviewsComment='" + reviewsComment + '\'' +
                ", reviewsCreationDate=" + reviewsCreationDate +
                '}';
    }
}
