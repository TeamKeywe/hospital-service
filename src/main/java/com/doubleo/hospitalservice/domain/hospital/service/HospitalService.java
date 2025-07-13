package com.doubleo.hospitalservice.domain.hospital.service;

import com.doubleo.hospitalservice.domain.hospital.dto.response.HospitalInfoResponse;
import java.util.List;

public interface HospitalService {

    List<HospitalInfoResponse> getAllHospitals();

    HospitalInfoResponse getHospitalById(Long id);
}
