package com.example.restapi.controller;

import com.example.restapi.dto.ReviewsDTO;
import com.example.restapi.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ReviewsRestController {

    @Autowired
    private ReviewsService reviewsService;

    @PostMapping("reviews")
    public ReviewsDTO createReviews(@RequestBody ReviewsDTO reviewsDTO) {
        return reviewsService.addReviews(reviewsDTO);
    }

    @GetMapping("reviews")
    public List<ReviewsDTO> getAllReviews() {
        return reviewsService.readReviews();
    }

    @GetMapping("reviews/{id}")
    public Optional<ReviewsDTO> getOneReviews(@PathVariable("id") UUID id) {
        return reviewsService.readOneReviews(id);
    }

    @PutMapping("reviews/{id}")
    public boolean UpdateReviews(@RequestBody ReviewsDTO reviewsDTO) {
        boolean resultat = false;
        try{
            resultat = reviewsService.updateReviews(reviewsDTO);
            resultat = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultat;
    }

    @DeleteMapping("reviews/{id}")
    public boolean DeleteReviews(@PathVariable("id") UUID id) {
        return reviewsService.deleteReviews(id);
    }
}


