package com.doubleo.hospitalservice.domain.hospital.controller;

import com.doubleo.hospitalservice.domain.hospital.dto.response.HospitalInfoResponse;
import com.doubleo.hospitalservice.domain.hospital.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalAdminController {

    private final HospitalService hospitalService;

    @Tag(name = "A-4. Hospital API", description = "병원 관련 API")
    @GetMapping(path = "/{hospitalId}")
    @Operation(summary = "Hospital Detail get API", description = "병원 상세 정보를 조회하기 위한 API")
    public HospitalInfoResponse hospitalDetailGet(@PathVariable Long hospitalId) {
        return hospitalService.getHospitalById(hospitalId);
    }
}
