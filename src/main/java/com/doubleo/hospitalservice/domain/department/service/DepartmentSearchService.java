package com.doubleo.hospitalservice.domain.department.service;

import com.doubleo.hospitalservice.domain.department.dto.response.DepartmentInfoResponse;
import com.doubleo.hospitalservice.domain.hospital.service.HospitalInternalService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentSearchService {

    // 병원 아이디로 진료과를 조회하는 서비스
    // 병원 ID → 테넌트 ID 조회 → 해당 테넌트에 속한 진료과 목록 반환
    // 단일 도메인 로직이 아닌 Hospital + Department 도메인이 사용되므로 별도의 SearchService로 분리함

    private final HospitalInternalService hospitalInternalService;
    private final DepartmentService departmentService;

    public List<DepartmentInfoResponse> getDepartmentListByHospitalId(Long hospitalId) {

        // hospitalInternalService에서 hospitalId에 해당하는 tenantId 조회
        String tenantId = hospitalInternalService.getTenantIdByHospitalId(hospitalId);

        // tenantId를 기반으로 진료과 조회 후 반환
        return departmentService.getDepartmentListByTenantId(tenantId);
    }
}
