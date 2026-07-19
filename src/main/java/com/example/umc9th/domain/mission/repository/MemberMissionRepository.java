package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 내가 진행중 / 진행 완료한 미션 조회
    Page<MemberMission> findByMemberIdAndIsComplete(Long memberId, Boolean isComplete, Pageable pageable);
    // 특정 회원의 전체 미션 조회
    Page<MemberMission> findByMemberId(Long memberId, Pageable pageable);
    // 이미 해당 미션에 도전했는지 확인
    boolean existsByMemberIdAndMissionId(
            Long memberId,
            Long missionId
    );
}
