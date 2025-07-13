package com.doubleo.hospitalservice.domain.area.service;

import com.doubleo.hospitalservice.domain.area.domain.Area;
import com.doubleo.hospitalservice.domain.area.dto.response.AreaInfoResponse;
import com.doubleo.hospitalservice.domain.area.repository.AreaRepository;
import com.doubleo.hospitalservice.domain.building.repository.BuildingRepository;
import com.doubleo.hospitalservice.global.config.util.TenantValidator;
import com.doubleo.hospitalservice.global.exception.CommonException;
import com.doubleo.hospitalservice.global.exception.errorcode.BuildingErrorCode;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final BuildingRepository buildingRepository;
    private final TenantValidator tenantValidator;

    public List<AreaInfoResponse> getAreaListByBuildingId(Long buildingId) {

        // 실제로 존재하는 건물인지 확인
        if (!buildingRepository.existsById(buildingId)) {
            throw new CommonException(BuildingErrorCode.BUILDING_NOT_FOUND);
        }

        // 건물별 구역 조회
        return areaRepository.findAllByBuilding_BuildingId(buildingId).stream()
                .map(AreaInfoResponse::from)
                .toList();
    }

    public Page<AreaInfoResponse> pagedAreaListGetByBuildingId(
            Long buildingId, String keyword, Pageable pageable) {

        String tenantId = tenantValidator.getTenantId();

        if (!buildingRepository.existsByTenantIdAndBuildingId(tenantId, buildingId)) {
            throw new CommonException(BuildingErrorCode.BUILDING_NOT_FOUND);
        }

        Page<Area> areaPage;

        if (keyword == null || keyword.isBlank()) {
            areaPage = areaRepository.findByBuilding_BuildingId(buildingId, pageable);
        } else {
            areaPage =
                    areaRepository.findByBuilding_BuildingIdAndAreaNameContainingIgnoreCase(
                            buildingId, keyword, pageable);
        }

        return areaPage.map(AreaInfoResponse::from);
    }
}
