package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.code.MemberErrorCode;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.Exception.MissionException;
import com.example.umc9th.domain.mission.Exception.code.MissionErrorCode;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandService {

    private static final Long TEST_MEMBER_ID = 1L;

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 미션 도전하기
    public MissionResDTO.ChallengeMissionResult challengeMission(Long missionId) {
        Member member = memberRepository
                .findByIdAndDeletedAtIsNull(TEST_MEMBER_ID)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Mission mission = missionRepository
                .findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));
        boolean alreadyChallenging = memberMissionRepository.existsByMemberIdAndMissionId(TEST_MEMBER_ID, missionId);

        if(alreadyChallenging) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING);
        }

        MemberMission memberMission = MissionConverter.toMemberMission(member, mission);
        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        return MissionConverter.toChallengeMissionResult(savedMemberMission);
    }
}
