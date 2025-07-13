package com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response;

import com.doubleo.hospitalservice.domain.hospitalpolicy.domain.HospitalPolicy;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalTime;

public record HospitalPolicyInfoResponse(
        @Schema(description = "예약 가능일 수 (오늘로부터 며칠 후까지 가능한지)", example = "3") int reserveDayOffset,
        @Schema(description = "예약 마감 시간", example = "09:00:00") LocalTime cutoffTime,
        @Schema(description = "환자별 허용 보호자 수", example = "1L") Long maxGuardianNum) {
    public static HospitalPolicyInfoResponse fromEntity(HospitalPolicy policy) {
        return new HospitalPolicyInfoResponse(
                policy.getReserveDayOffset(), policy.getCutoffTime(), policy.getMaxGuardianNum());
    }
}
