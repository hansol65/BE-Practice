package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;

public class MissionConverter {
    public static MemberMission toMemberMission(
            Member member, Mission mission
    ) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .isComplete(false)
                .build();
    }
    // MemberMission -> 응답 DTO
    public static MissionResDTO.ChallengeMissionResult
    toChallengeMissionResult(MemberMission memberMission) {
        return MissionResDTO.ChallengeMissionResult.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .memberId(memberMission.getMember().getId())
                .isComplete(memberMission.getIsComplete())
                .build();
    }
}
