package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

public class ReviewConverter {
    public static ReviewResDTO.MyReview toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReview.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .score(review.getScore())
                .build();
    }
    // 리뷰 작성 요청 DTO -> Review Entity
    public static Review toReview(
            ReviewReqDTO.CreateReviewDTO dto,
            Member member,
            Store store
    ) {
        return Review.builder()
                .body(dto.body())
                .score(dto.score())
                .member(member)
                .store(store)
                .build();
    }
    // 저장된 Review Entity -> 리뷰 작성 결과 DTO
    public static ReviewResDTO.CreateReviewResult toCreateReviewResult(
            Review review
    ) {
        return ReviewResDTO.CreateReviewResult.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .memberId(review.getMember().getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
