package com.doubleo.hospitalservice.domain.hospital.dto.response;

import com.doubleo.hospitalservice.domain.hospital.domain.Hospital;
import io.swagger.v3.oas.annotations.media.Schema;

public record HospitalInfoResponse(
        @Schema(description = "병원 아이디", example = "1") Long hospitalId,
        @Schema(description = "병원 이름", example = "서울아산병원") String hospitalName) {
    public static HospitalInfoResponse from(Hospital hospital) {
        return new HospitalInfoResponse(
                hospital.getId(), hospital.getHospitalName().replaceAll(" ", ""));
    }
}
