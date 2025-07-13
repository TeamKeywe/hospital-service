package com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;

public record HospitalAvailableDateResponse(
        @Schema(
                        description = "예약 가능한 날짜 목록",
                        example = "[\"2025-05-13\", \"2025-05-14\", \"2025-05-15\"]")
                @JsonFormat(pattern = "yyyy-MM-dd")
                List<LocalDate> availableDates) {}
