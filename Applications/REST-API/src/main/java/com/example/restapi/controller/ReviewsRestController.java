package com.example.restapi.controller;

import com.example.restapi.entity.Reviews;
import com.example.restapi.service.ReviewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/")
public class ReviewsRestController {

private final ReviewsService reviewsService;

public ReviewsRestController(ReviewsService reviewsService){
        this.reviewsService = reviewsService;
        }
@PostMapping("reviews")
public Reviews createReviews(@RequestBody Reviews reviews) {
        reviews.setReviewsId(UUID.randomUUID());
        reviewsService.addReviews(reviews);
        return reviews;
        }

@GetMapping("reviews")
public List<Reviews> getAllReviews() {
        return reviewsService.readReviews();
        }

@GetMapping("review/{id}")
public Optional<Reviews> getOneReviews(@PathVariable("id") UUID id) {
        return reviewsService.readOneReview(id);
        }

@PutMapping("review/{id}")
public boolean UpdateReviews(@PathVariable("id") UUID id, @RequestBody Reviews reviews) {
        Optional<Reviews> review_test = reviewsService.readOneReview(id);
        boolean resultat = false;
        if(review_test!=null) {
        resultat = reviewsService.updateReviews(reviews);
        }
        return resultat;
        }

@DeleteMapping("review/{id}")
public boolean DeleteReviews(@PathVariable("id") UUID id) {
        return reviewsService.deleteReviews(id);
        }
        }
