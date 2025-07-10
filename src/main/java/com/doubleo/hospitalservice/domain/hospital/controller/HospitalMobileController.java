package com.doubleo.hospitalservice.domain.hospital.controller;

import com.doubleo.hospitalservice.domain.hospital.dto.response.HospitalInfoResponse;
import com.doubleo.hospitalservice.domain.hospital.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalMobileController {

    private final HospitalService hospitalService;

    @Tag(name = "M-4. Hospital API", description = "병원 관련 API")
    @GetMapping
    @Operation(summary = "All Hospitals get API", description = "모든 병원을 조회하기 위한 API")
    public List<HospitalInfoResponse> hospitalListGetAll() {
        return hospitalService.getAllHospitals();
    }
}
