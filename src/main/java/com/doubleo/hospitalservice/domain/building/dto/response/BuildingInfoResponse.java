package com.doubleo.hospitalservice.domain.building.dto.response;

import com.doubleo.hospitalservice.domain.building.domain.Building;
import io.swagger.v3.oas.annotations.media.Schema;

public record BuildingInfoResponse(
        @Schema(description = "건물 아이디", example = "1") Long buildingId,
        @Schema(description = "건물 이름", example = "서울아산병원 A동") String buildingName,
        @Schema(description = "건물 코드", example = "A") String buildingCode) {
    public static BuildingInfoResponse from(Building building) {
        return new BuildingInfoResponse(
                building.getBuildingId(), building.getBuildingName(), building.getBuildingCode());
    }
}
