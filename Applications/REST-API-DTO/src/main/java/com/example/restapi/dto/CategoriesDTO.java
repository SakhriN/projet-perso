package com.example.restapi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriesDTO {

    @Id
    private UUID categoriesId;

    private String categoriesName, categoriesDescription;
}
