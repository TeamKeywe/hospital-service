package com.doubleo.hospitalservice.domain.hospitalpolicy.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.doubleo.hospitalservice.domain.hospitalpolicy.domain.HospitalPolicy;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.request.HospitalPolicyInfoRequest;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.response.HospitalPolicyInfoResponse;
import com.doubleo.hospitalservice.domain.hospitalpolicy.repository.HospitalPolicyRepository;
import com.doubleo.hospitalservice.global.config.util.TenantValidator;
import com.doubleo.hospitalservice.global.exception.CommonException;
import com.doubleo.hospitalservice.global.exception.errorcode.HospitalPolicyErrorCode;
import com.doubleo.tenantcontext.TenantContextHolder;
import java.time.LocalTime;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.*;

class HospitalPolicyServiceImplTest {

    @InjectMocks private HospitalPolicyServiceImpl hospitalPolicyService;

    @Mock private HospitalPolicyRepository hospitalPolicyRepository;

    @Mock private TenantValidator<HospitalPolicy> tenantValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPolicyByTenantId_성공() {

        // given
        TenantContextHolder.setTenantId("101");
        String tenantId = tenantValidator.getTenantId();

        HospitalPolicy policy =
                HospitalPolicy.builder()
                        .id(1L)
                        .reserveDayOffset(3)
                        .cutoffTime(LocalTime.of(9, 0))
                        .build();

        when(hospitalPolicyRepository.getHospitalPolicyByTenantId(tenantId))
                .thenReturn(Optional.of(policy));

        // when
        HospitalPolicyInfoResponse result = hospitalPolicyService.getPolicyByTenantId();

        // then
        assertEquals(3, policy.getReserveDayOffset());
        assertEquals(LocalTime.of(9, 0), policy.getCutoffTime());
        verify(tenantValidator).validateTenant(policy);

        TenantContextHolder.clear();
    }

    @Test
    void getPolicyByTenantId_정책없음_예외() {
        // given
        TenantContextHolder.setTenantId("101");
        String tenantId = tenantValidator.getTenantId();

        when(hospitalPolicyRepository.getHospitalPolicyByTenantId(tenantId))
                .thenReturn(Optional.empty());

        // when & then
        CommonException ex =
                assertThrows(
                        CommonException.class, () -> hospitalPolicyService.getPolicyByTenantId());
        assertEquals(HospitalPolicyErrorCode.HOSPITAL_POLICY_NOT_FOUND, ex.getErrorCode());

        TenantContextHolder.clear();
    }

    @Test
    void updatePolicyByTenantId_성공() {
        // given
        TenantContextHolder.setTenantId("101");
        String tenantId = tenantValidator.getTenantId();

        HospitalPolicy policy =
                HospitalPolicy.builder()
                        .id(1L)
                        .reserveDayOffset(3)
                        .cutoffTime(LocalTime.of(9, 0))
                        .build();

        HospitalPolicyInfoRequest request =
                new HospitalPolicyInfoRequest(5, LocalTime.of(11, 0), 2L);

        when(hospitalPolicyRepository.getHospitalPolicyByTenantId(tenantId))
                .thenReturn(Optional.of(policy));

        // when
        hospitalPolicyService.updatePolicyByTenantId(request);

        // then
        assertEquals(5, policy.getReserveDayOffset());
        assertEquals(LocalTime.of(11, 0), policy.getCutoffTime());
        verify(tenantValidator).validateTenant(policy);

        TenantContextHolder.clear();
    }

    @Test
    void updatePolicyByTenantId_정책없음_예외() {
        // given
        TenantContextHolder.setTenantId("101");
        String tenantId = tenantValidator.getTenantId();
        HospitalPolicyInfoRequest request =
                new HospitalPolicyInfoRequest(5, LocalTime.of(11, 0), 2L);

        when(hospitalPolicyRepository.getHospitalPolicyByTenantId(tenantId))
                .thenReturn(Optional.empty());

        // when & then
        CommonException ex =
                assertThrows(
                        CommonException.class,
                        () -> hospitalPolicyService.updatePolicyByTenantId(request));
        assertEquals(HospitalPolicyErrorCode.HOSPITAL_POLICY_NOT_FOUND, ex.getErrorCode());

        TenantContextHolder.clear();
    }
}
