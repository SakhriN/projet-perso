package com.example.restapi.service;

import com.example.restapi.entity.Reviews;
import com.example.restapi.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;


    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }


    public Reviews addReviews(Reviews reviews) {
        return reviewsRepository.save(reviews);
    }

    public Optional<Reviews> readOneReview(UUID id) {
        return reviewsRepository.findById(id);
    }

    public List<Reviews> readReviews() {
        return reviewsRepository.findAll();
    }

    public boolean updateReviews(Reviews reviews) {
        boolean test = false;
        try {
            reviewsRepository.save(reviews);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }


    public boolean deleteReviews(UUID id) {
        boolean test = false;
        try {
            reviewsRepository.deleteById(id);
            test = true;

        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
        return test;
    }

}
