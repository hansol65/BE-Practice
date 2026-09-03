package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.global.validation.annotation.ValidPage;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.apiPayload.ApiResponse;

import com.example.umc9th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/{memberId}/reviews")
    public ApiResponse<Page<ReviewResDTO.MyReview>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer score,
            @ValidPage
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Page<ReviewResDTO.MyReview> result = reviewQueryService.getMyReviews(
                memberId,
                storeName,
                score,
                page
        );
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
