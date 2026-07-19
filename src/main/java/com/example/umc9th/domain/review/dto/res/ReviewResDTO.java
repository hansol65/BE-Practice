package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Getter
    @Builder
    public static class MyReview {
        private Long reviewId;
        private Long storeId;
        private String storeName;
        private Float score;
    }
    @Getter
    @Builder
    public static class CreateReviewResult {
        private Long reviewId;
        private Long storeId;
        private Long memberId;
        private LocalDateTime createdAt;
    }
}
