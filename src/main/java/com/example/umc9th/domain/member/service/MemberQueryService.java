package com.example.umc9th.domain.member.service;


import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    // 마이페이지 조회
    public Member getMyPage(Long memberId) {
        return memberRepository.findByIdAndDeletedAtIsNull(memberId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }
}
