package com.doubleo.hospitalservice.domain.department.service;

import com.doubleo.hospitalservice.domain.department.dto.response.DepartmentInfoResponse;
import java.util.List;

public interface DepartmentService {
    List<DepartmentInfoResponse> getDepartmentListByTenantId(String tenantId);
}
