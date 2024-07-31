package com.example.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Products {

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

    @JsonIgnore
    @OneToMany(mappedBy = "products")
    private List<OrderItems> orderItemsList;

    @JsonIgnore
    @OneToMany(mappedBy = "products")
    private List<CartItems> cartItemsList;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @JsonIgnore
    @OneToMany(mappedBy = "products")
    private List<Reviews> reviewsList;

    @Override
    public String toString() {
        return "Products{" +
                "productsId=" + productsId +
                ", productsTitle='" + productsTitle + '\'' +
                ", productsDescription='" + productsDescription + '\'' +
                ", productsPublisher='" + productsPublisher + '\'' +
                ", productsPlatform='" + productsPlatform + '\'' +
                ", productsImage='" + productsImage + '\'' +
                ", products_price=" + productsPrice +
                ", products_stock_quantity=" + productsStockQuantity +
                ", products_release_date=" + productsReleaseDate +
                '}';
    }
}
