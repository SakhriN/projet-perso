package com.example.restapi.service;

import com.example.restapi.entity.Reviews;
import com.example.restapi.dto.ReviewsDTO;
import com.example.restapi.mapper.ReviewsMapper;
import com.example.restapi.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final ReviewsMapper reviewsMapper;

    @Autowired
    public ReviewsService(ReviewsRepository reviewsRepository, ReviewsMapper reviewsMapper) {
        this.reviewsRepository = reviewsRepository;
        this.reviewsMapper = reviewsMapper;
    }

    public ReviewsDTO addReviews(ReviewsDTO reviewsDTO) {
        Reviews reviews = reviewsMapper.toReviews(reviewsDTO);
        return reviewsMapper.toReviewsDto(reviewsRepository.save(reviews));
    }

    public Optional<ReviewsDTO> readOneReviews(UUID id) {
        return reviewsRepository.findById(id).map(reviewsMapper::toReviewsDto);
    }

    public List<ReviewsDTO> readReviews() {
        return reviewsRepository.findAll().stream()
                .map(reviewsMapper::toReviewsDto)
                .collect(Collectors.toList());
    }

    public boolean updateReviews(ReviewsDTO reviewsDTO) {
        boolean test = false;
        try {
            Reviews reviews = reviewsMapper.toReviews(reviewsDTO);
            reviewsRepository.save(reviews);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

    public boolean deleteReviews(UUID id) {
        boolean test = false;
        try {
            reviewsRepository.deleteById(id);
            test = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return test;
    }

}
