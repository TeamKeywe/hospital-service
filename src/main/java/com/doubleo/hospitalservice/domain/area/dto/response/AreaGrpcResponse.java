package com.doubleo.hospitalservice.domain.area.dto.response;

import com.doubleo.hospitalservice.domain.area.domain.Area;

public record AreaGrpcResponse(
        Long id, String tenantId, Long buildingId, String areaName, String areaCode) {
    public static AreaGrpcResponse fromEntity(Area area) {
        return new AreaGrpcResponse(
                area.getId(),
                String.valueOf(area.getTenantId()),
                area.getBuilding().getBuildingId(),
                area.getAreaName(),
                area.getAreaCode());
    }
}
