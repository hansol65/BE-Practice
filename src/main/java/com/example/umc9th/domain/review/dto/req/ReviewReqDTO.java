package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record CreateReviewDTO (

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        String body,

        @NotNull(message = "리뷰 점수는 필수입니다.")
        @DecimalMin(
                value = "1.0",
                message = "리뷰 점수는 1점 이상이어야 합니다."
        )

        @DecimalMax(
                value = "5.0",
                message = "리뷰 점수는 5점 이하여야 합니다."
        )
        Float score
    ) { }
}
