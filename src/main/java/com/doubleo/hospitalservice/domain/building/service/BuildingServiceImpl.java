package com.doubleo.hospitalservice.domain.building.service;

import com.doubleo.hospitalservice.domain.building.domain.Building;
import com.doubleo.hospitalservice.domain.building.dto.response.BuildingInfoResponse;
import com.doubleo.hospitalservice.domain.building.repository.BuildingRepository;
import com.doubleo.hospitalservice.domain.hospitalpolicy.domain.HospitalPolicy;
import com.doubleo.hospitalservice.global.config.util.TenantValidator;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final TenantValidator<HospitalPolicy> tenantValidator;

    // tenantId를 기반으로 소속 건물 리스트 조회
    @Override
    public List<BuildingInfoResponse> getBuildingListByTenantId() {

        String tenantId = tenantValidator.getTenantId();

        List<Building> buildings = buildingRepository.findAllByTenantId(tenantId);

        return buildings.stream().map(BuildingInfoResponse::from).toList();
    }

    public Page<BuildingInfoResponse> getPagedBuildingListByTenantId(
            String keyword, Pageable pageable) {
        String tenantId = tenantValidator.getTenantId();
        Page<Building> buildings;

        if (keyword != null && !keyword.trim().isEmpty()) {
            buildings =
                    buildingRepository.findByTenantIdAndBuildingNameContainingIgnoreCase(
                            tenantId, keyword, pageable);
        } else {
            buildings = buildingRepository.findByTenantId(tenantId, pageable);
        }

        return buildings.map(
                building ->
                        new BuildingInfoResponse(
                                building.getBuildingId(),
                                building.getBuildingName(),
                                building.getBuildingCode()));
    }
}
