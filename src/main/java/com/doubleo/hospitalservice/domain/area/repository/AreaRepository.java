package com.doubleo.hospitalservice.domain.area.repository;

import com.doubleo.hospitalservice.domain.area.domain.Area;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findAllByBuilding_BuildingId(Long buildingId);

    Page<Area> findByBuilding_BuildingId(Long buildingId, Pageable pageable);

    Optional<Area> findByTenantIdAndAreaCode(String tenantId, String areaCode);

    Page<Area> findByBuilding_BuildingIdAndAreaNameContainingIgnoreCase(
            Long buildingId, String keyword, Pageable pageable);
}
