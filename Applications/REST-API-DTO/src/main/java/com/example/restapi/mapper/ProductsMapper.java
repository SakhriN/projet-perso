package com.example.restapi.mapper;

import com.example.restapi.dto.ProductsDTO;
import com.example.restapi.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductsMapper {

        @Mapping(source = "categoriesId", target = "categories.categoriesId")
        Products toProducts(ProductsDTO productsDTO);

        @Mapping(source = "categories.categoriesId", target = "categoriesId")
        ProductsDTO toProductsDto(Products products);
        }
