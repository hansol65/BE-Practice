package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;
import lombok.Getter;

public class MissionResDTO {
    @Getter
    @Builder
    public static class ChallengeMissionResult {
        private Long memberMissionId;
        private Long missionId;
        private Long memberId;
        private Boolean isComplete;
    }
}
