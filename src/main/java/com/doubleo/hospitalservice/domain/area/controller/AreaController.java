package com.doubleo.hospitalservice.domain.area.controller;

import com.doubleo.hospitalservice.domain.area.dto.response.AreaInfoResponse;
import com.doubleo.hospitalservice.domain.area.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/{buildingId}/areas")
    @Operation(summary = "Get Areas by Building ID", description = "건물 ID로 구역 리스트 조회")
    public List<AreaInfoResponse> areaListGetByBuildingId(@PathVariable Long buildingId) {
        return areaService.getAreaListByBuildingId(buildingId);
    }

    @GetMapping("/{buildingId}/areas/paged")
    @Operation(
            summary = "Get Paged Areas by Building ID",
            description = "건물 ID로 구역 리스트 조회 - 페이지네이션")
    public Page<AreaInfoResponse> pagedAreaListGetByBuildingId(
            @PathVariable Long buildingId,
            @RequestParam(required = false) String keyword,
            Pageable pageable) {
        return areaService.pagedAreaListGetByBuildingId(buildingId, keyword, pageable);
    }
}
