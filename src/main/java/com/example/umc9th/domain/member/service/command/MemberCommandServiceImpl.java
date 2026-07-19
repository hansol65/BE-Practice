package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ) {
        // 1. 요청 DTO를 Member 엔티티로 변환
        Member member = MemberConverter.toMember(dto);

        // 2. Member 엔티티를 DB에 저장
        Member savedMember = memberRepository.save(member);

        // 3. 저장된 Member를 응답 DTO로 변환
        return MemberConverter.toJoinDto(savedMember);
    }
}
