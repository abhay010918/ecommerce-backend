package com.ecommerce.backend.service.services;

import com.ecommerce.backend.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO addReview(Long userId, Long productId, ReviewDTO reviewDTO);
    List<ReviewDTO> getReviewsForProduct(Long productId);
    void deleteReview(Long reviewId);
}
