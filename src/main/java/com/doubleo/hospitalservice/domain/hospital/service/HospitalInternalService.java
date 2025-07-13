package com.doubleo.hospitalservice.domain.hospital.service;

// 병원 내부 서비스
public interface HospitalInternalService {
    String getTenantIdByHospitalId(Long hospitalId);
}
