package com.doubleo.hospitalservice.domain.hospitalpolicy.controller;

import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.request.HospitalPolicyInfoRequest;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response.HospitalPolicyInfoResponse;
import com.doubleo.hospitalservice.domain.hospitalpolicy.service.HospitalPolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "A-4. Hospital Policy API", description = "병원 정책 관련 API")
@RestController
@RequestMapping("/hospitals/policies")
@RequiredArgsConstructor
public class HospitalAdminPolicyController {

    private final HospitalPolicyService hospitalPolicyService;

    @GetMapping
    @Operation(summary = "Hospital Policy get API", description = "병원의 정책을 조회하기 위한 API")
    public HospitalPolicyInfoResponse myHospitalPolicyGet() {
        return hospitalPolicyService.getPolicyByTenantId();
    }

    @PatchMapping
    @Operation(summary = "Hospital Policy update API", description = "병원의 정책을 수정하기 위한 API")
    public void myHospitalPolicyUpdate(@Valid @RequestBody HospitalPolicyInfoRequest request) {
        hospitalPolicyService.updatePolicyByTenantId(request);
    }
}
