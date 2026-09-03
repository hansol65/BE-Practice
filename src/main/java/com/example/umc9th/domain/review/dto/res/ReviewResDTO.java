package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

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

    @Getter
    @Builder
    public static class MyReviewList {
        private List<MyReview> reviewList;

        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
