package com.doubleo.hospitalservice.domain.department.service;

import com.doubleo.hospitalservice.domain.department.domain.Department;
import com.doubleo.hospitalservice.domain.department.dto.response.DepartmentInfoResponse;
import com.doubleo.hospitalservice.domain.department.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    // tenantId를 기반으로 진료과 조회
    public List<DepartmentInfoResponse> getDepartmentListByTenantId(String tenantId) {
        List<Department> departments = departmentRepository.findAllByTenantId(tenantId);

        return departments.stream().map(DepartmentInfoResponse::from).toList();
    }
}
