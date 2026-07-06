package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

public class ReviewResDTO {
    @Getter
    @Builder
    public static class MyReview {
        private Long reviewId;
        private Long storeId;
        private String storeName;
        private Float score;
    }
}
