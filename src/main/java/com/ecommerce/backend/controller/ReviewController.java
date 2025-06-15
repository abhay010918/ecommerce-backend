package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.ReviewDTO;
import com.ecommerce.backend.service.services.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("add/{userId}/{productId}")
    public ResponseEntity<ReviewDTO> addReview(
            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestBody ReviewDTO reviewDTO
    ){
        return ResponseEntity.ok(reviewService.addReview(userId,productId,reviewDTO));
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewByProductId(@PathVariable Long productId){
        return ResponseEntity.ok(reviewService.getReviewsForProduct(productId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }

}
