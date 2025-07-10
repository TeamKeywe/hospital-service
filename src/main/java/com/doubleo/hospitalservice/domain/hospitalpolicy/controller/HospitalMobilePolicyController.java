package com.doubleo.hospitalservice.domain.hospitalpolicy.controller;

import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response.HospitalAvailableDateResponse;
import com.doubleo.hospitalservice.domain.hospitalpolicy.service.HospitalPolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "M-4. Hospital Policy API", description = "병원 정책 관련 API")
@RestController
@RequestMapping("/hospitals/policies")
@RequiredArgsConstructor
public class HospitalMobilePolicyController {

    private final HospitalPolicyService hospitalPolicyService;

    @GetMapping("/available-dates")
    @Operation(
            summary = "Hospital Available Dates get API",
            description = "병원의 정책에 따른 예약 가능 날짜를 조회하기 위한 API")
    public HospitalAvailableDateResponse availableDatesGet() {
        return hospitalPolicyService.getAvailableDateListByTenantId();
    }
}
