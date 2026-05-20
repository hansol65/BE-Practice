package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    // 홈 화면
    public Page<Mission> getHomeMissions(String locationName, Pageable pageable) {
        return missionRepository.findByStoreLocationName(locationName, pageable);
    }

    // 내가 진행중인 미션
    public Page<MemberMission> getProgressingMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findByMemberIdAndIsComplete(memberId, false, pageable);
    }
    // 내가 완료한 미션
    public Page<MemberMission> getCompletedMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findByMemberIdAndIsComplete(memberId, true, pageable);
    }

}
