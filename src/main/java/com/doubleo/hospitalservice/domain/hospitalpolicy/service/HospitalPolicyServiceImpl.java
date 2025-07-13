package com.doubleo.hospitalservice.domain.hospitalpolicy.service;

import com.doubleo.hospitalservice.domain.hospitalpolicy.domain.HospitalPolicy;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.request.HospitalPolicyInfoRequest;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response.HospitalAvailableDateResponse;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response.HospitalPolicyInfoResponse;
import com.doubleo.hospitalservice.domain.hospitalpolicy.repository.HospitalPolicyRepository;
import com.doubleo.hospitalservice.global.config.util.TenantValidator;
import com.doubleo.hospitalservice.global.exception.CommonException;
import com.doubleo.hospitalservice.global.exception.errorcode.HospitalPolicyErrorCode;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class HospitalPolicyServiceImpl implements HospitalPolicyService {

    private final HospitalPolicyRepository hospitalPolicyRepository;
    private final TenantValidator<HospitalPolicy> tenantValidator;

    @Override
    public HospitalPolicyInfoResponse getPolicyByTenantId() {

        String tenantId = tenantValidator.getTenantId();

        HospitalPolicy policy =
                hospitalPolicyRepository
                        .getHospitalPolicyByTenantId(tenantId)
                        .orElseThrow(
                                () ->
                                        new CommonException(
                                                HospitalPolicyErrorCode.HOSPITAL_POLICY_NOT_FOUND));

        tenantValidator.validateTenant(policy);

        return HospitalPolicyInfoResponse.fromEntity(policy);
    }

    @Transactional
    public void updatePolicyByTenantId(HospitalPolicyInfoRequest request) {

        String tenantId = tenantValidator.getTenantId();

        HospitalPolicy policy =
                hospitalPolicyRepository
                        .getHospitalPolicyByTenantId(tenantId)
                        .orElseThrow(
                                () ->
                                        new CommonException(
                                                HospitalPolicyErrorCode.HOSPITAL_POLICY_NOT_FOUND));
        tenantValidator.validateTenant(policy);

        policy.updatePolicyInfo(request);
    }

    @Override
    public HospitalAvailableDateResponse getAvailableDateListByTenantId() {
        String tenantId = tenantValidator.getTenantId();

        HospitalPolicy policy =
                hospitalPolicyRepository
                        .getHospitalPolicyByTenantId(tenantId)
                        .orElseThrow(
                                () ->
                                        new CommonException(
                                                HospitalPolicyErrorCode.HOSPITAL_POLICY_NOT_FOUND));

        List<LocalDate> dates = calculateAvailableDates(policy, LocalDateTime.now());
        return new HospitalAvailableDateResponse(dates);
    }

    private List<LocalDate> calculateAvailableDates(HospitalPolicy policy, LocalDateTime now) {

        // 기준 시간을 넘었는지
        boolean isAfterCutoff = now.toLocalTime().isAfter(policy.getCutoffTime());
        // 기준 시간을 전이면 첫날짜를 오늘로, 이후면 다음날로 설정
        LocalDate baseDate = isAfterCutoff ? now.toLocalDate().plusDays(1) : now.toLocalDate();

        // 첫 날짜 기준으로 ReserveDayOffset만큼 날짜를 예약 가능 날짜로 설정
        return IntStream.range(0, policy.getReserveDayOffset())
                .mapToObj(baseDate::plusDays)
                .collect(Collectors.toList());
    }
}
