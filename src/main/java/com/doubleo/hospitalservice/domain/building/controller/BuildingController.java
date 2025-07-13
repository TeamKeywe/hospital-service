package com.doubleo.hospitalservice.domain.building.controller;

import com.doubleo.hospitalservice.domain.building.dto.response.BuildingInfoResponse;
import com.doubleo.hospitalservice.domain.building.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospitals/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping
    @Operation(
            summary = "Get Buildings by Hospital Tenant ID",
            description = "병원 테넌트 id를 기준으로 건물 리스트를 조회하는 API")
    public List<BuildingInfoResponse> buildingListGetByTenantId() {
        return buildingService.getBuildingListByTenantId();
    }

    @GetMapping("/paged")
    @Operation(
            summary = "Get Paged Buildings by Hospital Tenant ID",
            description = "병원 테넌트 id를 기준으로 건물 리스트를 페이지네이션하여 조회하는 API")
    public Page<BuildingInfoResponse> pagedBuildingListByTenantIdGet(
            Pageable pageable, @RequestParam(required = false) String keyword) {
        return buildingService.getPagedBuildingListByTenantId(keyword, pageable);
    }
}
