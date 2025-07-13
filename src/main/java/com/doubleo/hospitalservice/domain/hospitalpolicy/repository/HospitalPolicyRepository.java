package com.doubleo.hospitalservice.domain.hospitalpolicy.repository;

import com.doubleo.hospitalservice.domain.hospitalpolicy.domain.HospitalPolicy;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalPolicyRepository extends JpaRepository<HospitalPolicy, Long> {
    Optional<HospitalPolicy> getHospitalPolicyByTenantId(String tenantId);
}
