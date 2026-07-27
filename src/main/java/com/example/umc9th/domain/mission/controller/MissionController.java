package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.Exception.code.MissionSuccessCode;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.MissionCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{missionId}/challenge")
    public ResponseEntity<
            ApiResponse<MissionResDTO.ChallengeMissionResult>
            > challengeMission(
                    @PathVariable("missionId") Long missionId
    ) {
        MissionSuccessCode code = MissionSuccessCode.CHALLENGE_CREATED;
        MissionResDTO.ChallengeMissionResult result = missionCommandService.challengeMission(missionId);

        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }
}
