package com.doubleo.hospitalservice.domain.hospitalpolicy.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

public record HospitalPolicyInfoRequest(
        @Schema(description = "예약 가능일 수 (오늘로부터 며칠 후까지 가능한지)", example = "3")
                @NotNull(message = "예약 가능일 수는 필수입니다.")
                int reserveDayOffset,
        @Schema(description = "예약 마감 시간", example = "09:00:00")
                @NotNull(message = "예약 가능 시간은 필수입니다.")
                LocalTime cutoffTime,
        @Schema(description = "환자별 허용 보호자 수", example = "1L")
                @NotNull(message = "환자별 허용 보호자 수는 필수입니다.")
                Long maxGuardianNum) {}
