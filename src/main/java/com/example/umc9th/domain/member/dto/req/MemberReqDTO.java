package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.domain.store.enums.Address;
import com.example.umc9th.global.enums.SocialType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class MemberReqDTO {
    public record JoinDTO(
            @NotBlank(message = "이름은 필수입니다.")
            String name,

            @NotNull(message = "성별은 필수입니다.")
            Gender gender,

            @NotNull(message = "생년월일은 필수입니다.")
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,

            Address address,

            String detailAddress,

            String socialUid,

            SocialType socialType,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,
            String phoneNumber
    ) {

    }
}
