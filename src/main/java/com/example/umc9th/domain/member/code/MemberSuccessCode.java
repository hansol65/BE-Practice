package com.example.umc9th.domain.member.code;

import com.example.umc9th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    JOIN_SUCCESS(
            HttpStatus.CREATED,
            "MEMBER201_1",
            "회원가입에 성공했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}