package com.example.restapi.mapper;

import com.example.restapi.dto.CategoriesDTO;
import com.example.restapi.entity.Categories;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriesMapper {
        Categories toCategories(CategoriesDTO categoriesDTO);

        CategoriesDTO toCategoriesDto(Categories categories);
        }
