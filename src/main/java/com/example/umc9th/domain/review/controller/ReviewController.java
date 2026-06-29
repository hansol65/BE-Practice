package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{memberId}/reviews")
    public Page<Review> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer score,
            Pageable pageable
    ) {
        return reviewQueryService.getMyReviews(memberId, storeName, score, pageable);
    }
}
