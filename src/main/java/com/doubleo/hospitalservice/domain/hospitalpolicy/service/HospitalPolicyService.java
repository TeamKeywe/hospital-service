package com.doubleo.hospitalservice.domain.hospitalpolicy.service;

import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.request.HospitalPolicyInfoRequest;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response.HospitalAvailableDateResponse;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response.HospitalPolicyInfoResponse;

public interface HospitalPolicyService {
    HospitalPolicyInfoResponse getPolicyByTenantId();

    void updatePolicyByTenantId(HospitalPolicyInfoRequest request);

    HospitalAvailableDateResponse getAvailableDateListByTenantId();
}
