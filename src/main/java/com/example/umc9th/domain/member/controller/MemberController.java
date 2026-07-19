package com.example.umc9th.domain.member.controller;

import com.example.umc9th.domain.member.code.MemberSuccessCode;
import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.service.command.MemberCommandService;
import com.example.umc9th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<MemberResDTO.JoinDTO>> signUp(
            @Valid @RequestBody MemberReqDTO.JoinDTO dto
    ) {
        MemberSuccessCode code = MemberSuccessCode.JOIN_SUCCESS;

        MemberResDTO.JoinDTO result =
                memberCommandService.signup(dto);

        return ResponseEntity
                .status(code.getStatus())
                .body(ApiResponse.onSuccess(code, result));
    }
}