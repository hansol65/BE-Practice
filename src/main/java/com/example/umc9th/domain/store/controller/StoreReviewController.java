package com.example.umc9th.domain.store.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import com.example.umc9th.domain.review.code.ReviewSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreReviewController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResDTO.CreateReviewResult>>
    createReview(
            @PathVariable("storeId") Long storeId,
            @Valid @RequestBody ReviewReqDTO.CreateReviewDTO dto
            ) {
        ReviewSuccessCode code = ReviewSuccessCode.CREATED;

        ReviewResDTO.CreateReviewResult result = reviewCommandService.createReview(storeId, dto);

        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }
}
