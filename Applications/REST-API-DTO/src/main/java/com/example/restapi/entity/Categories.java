package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoriesId;

    private String categoriesName, categoriesDescription;

    @OneToMany(mappedBy = "categories")
    private List<Products> productsList;

}
