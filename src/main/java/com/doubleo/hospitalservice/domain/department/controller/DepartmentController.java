package com.doubleo.hospitalservice.domain.department.controller;

import com.doubleo.hospitalservice.domain.department.dto.response.DepartmentInfoResponse;
import com.doubleo.hospitalservice.domain.department.service.DepartmentSearchService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentSearchService departmentSearchService;

    @GetMapping
    @Operation(summary = "Get departments by hospitalId", description = "병원 ID로 진료과 목록 조회")
    public List<DepartmentInfoResponse> departmentListGetByHospitalId(
            @RequestParam Long hospitalId) {
        return departmentSearchService.getDepartmentListByHospitalId(hospitalId);
    }
}
