package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

public class ReviewConverter {
    public static ReviewResDTO.MyReview toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReview.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .build();
    }
}
