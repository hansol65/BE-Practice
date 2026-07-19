package com.example.umc9th.domain.mission.Exception;

import com.example.umc9th.global.apiPayload.code.BaseErrorCode;
import com.example.umc9th.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
    super(code);
    }
}
