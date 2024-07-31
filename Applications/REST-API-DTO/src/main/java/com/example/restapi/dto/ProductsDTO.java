package com.example.restapi.dto;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productsId;

    private String productsTitle,
            productsDescription,
            productsPublisher,
            productsPlatform,
            productsImage;
    private Double productsPrice;
    private Integer productsStockQuantity;
    private Date productsReleaseDate;
    private UUID categoriesId;

}
