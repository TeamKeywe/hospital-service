package com.doubleo.hospitalservice.domain.area.service;

import com.doubleo.hospitalservice.domain.area.dto.response.AreaInfoResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AreaService {
    List<AreaInfoResponse> getAreaListByBuildingId(Long buildingId);

    Page<AreaInfoResponse> pagedAreaListGetByBuildingId(
            Long buildingId, String keyword, Pageable pageable);
}
