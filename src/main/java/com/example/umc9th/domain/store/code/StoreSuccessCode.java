package com.example.umc9th.domain.store.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    JOIN_SUCCESS(
            HttpStatus.CREATED,
            "STORE201_1",
            "가게 조회에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}