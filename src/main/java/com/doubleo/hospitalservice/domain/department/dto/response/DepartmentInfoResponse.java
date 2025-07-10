package com.doubleo.hospitalservice.domain.department.dto.response;

import com.doubleo.hospitalservice.domain.department.domain.Department;
import io.swagger.v3.oas.annotations.media.Schema;

public record DepartmentInfoResponse(
        @Schema(description = "진료과 id", example = "1") Long departmentId,
        @Schema(description = "진료과 이름", example = "이비인후과") String departmentName,
        @Schema(description = "진료과 코드", example = "ENT") String departmentCode) {
    public static DepartmentInfoResponse from(Department department) {
        return new DepartmentInfoResponse(
                department.getDepartmentId(),
                department.getDepartmentName(),
                department.getDepartmentCode());
    }
}
