package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Review;
import java.util.List;

public interface ReviewService {
    Review addReview(Long userId, Long productId, Review review);
    List<Review> getReviewsForProduct(Long productId);
    void deleteReview(Long reviewId);
}
