package com.example.umc9th.domain.store.controller;


import com.example.umc9th.domain.global.validation.annotation.ValidPage;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreMissionController {

    private final MissionQueryService missionQueryService;

    @GetMapping("/{storeId}/missions")
    public Page<Mission> getStoreMissions(
            @PathVariable Long storeId,
            @ValidPage
            @RequestParam(defaultValue = "1") Integer page
    ) {
        Pageable pageable = PageRequest.of(page -1, 10);
        return missionQueryService.getStoreMissions(storeId, pageable);
    }
}
