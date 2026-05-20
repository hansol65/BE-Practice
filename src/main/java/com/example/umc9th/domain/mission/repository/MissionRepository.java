package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    // 홈 화면 쿼리
    // 현재 선택된 지역에서 도전 가능한 미션 목록 조회
    Page<Mission> findByStoreLocationName(String locationName, Pageable pageable);
    // 특정 가게의 미션 목록 조회
    Page<Mission> findByStoreId(Long storeId, Pageable pageable);
}
