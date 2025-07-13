package com.doubleo.hospitalservice.domain.area.dto.response;

import com.doubleo.hospitalservice.domain.area.domain.Area;
import io.swagger.v3.oas.annotations.media.Schema;

public record AreaInfoResponse(
        @Schema(description = "구역 아이디", example = "1") Long areaId,
        @Schema(description = "구역 이름", example = "A01 수술실") String areaName,
        @Schema(description = "구역 코드", example = "A01") String areaCode) {
    public static AreaInfoResponse from(Area area) {
        return new AreaInfoResponse(area.getId(), area.getAreaName(), area.getAreaCode());
    }
}
