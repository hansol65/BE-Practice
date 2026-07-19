package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {
    public static Member toMember(MemberReqDTO.JoinDTO dto) {
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .socialUid(dto.socialUid())
                .socialType(dto.socialType())
                .point(0)
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .build();
    }

    public static MemberResDTO.JoinDTO toJoinDto(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }
}
