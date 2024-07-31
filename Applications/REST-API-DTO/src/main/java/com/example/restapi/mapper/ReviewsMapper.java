package com.example.restapi.mapper;

import com.example.restapi.dto.ReviewsDTO;
import com.example.restapi.entity.Reviews;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewsMapper {
        @Mapping(source = "productsId", target = "products.productsId")
        Reviews toReviews(ReviewsDTO reviewsDTO);

        @Mapping(source = "products.productsId", target = "productsId")
        ReviewsDTO toReviewsDto(Reviews reviews);
        }
