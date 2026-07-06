package com.example.umc9th.domain.review.exception.code;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    INVALID_SCORE(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "리뷰 점수는 1점부터 5점까지만 조회할 수 있습니다."
    );
    private final HttpStatus status;
    private final String code;
    private final String message;
}
