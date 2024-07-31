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
public class ReviewsDTO {

    @Id
    private UUID reviewsId;
    private Double reviewsRating;
    private String reviewsComment;
    private Date reviewsCreationDate;
    private UUID productsId;
}
