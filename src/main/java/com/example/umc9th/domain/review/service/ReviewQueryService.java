package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public Page<Review> getMyReviews(
            Long memberId,
            String storeName,
            Integer score,
            Pageable pageable
    ) {
        return reviewRepository.findMyReviewsWithFilter(
                memberId,
                storeName,
                score,
                pageable
        );
    }
}
