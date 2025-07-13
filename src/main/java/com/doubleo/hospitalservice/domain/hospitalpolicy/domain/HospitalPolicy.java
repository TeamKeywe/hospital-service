package com.doubleo.hospitalservice.domain.hospitalpolicy.domain;

import com.doubleo.hospitalservice.domain.common.model.BaseEntity;
import com.doubleo.hospitalservice.domain.hospitalpolicy.dto.request.HospitalPolicyInfoRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hospital_policy")
public class HospitalPolicy extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_policy_id")
    private Long id;

    @Column(name = "hospital_policy_reserve_day_offset")
    @Min(value = 0, message = "사전 예약 가능 일수는 0 이상이어야 합니다.")
    @Schema(description = "며칠 전부터 예약이 가능한지 나타냅니다. (예: 3이면 3일 전부터 예약 가능)")
    private int reserveDayOffset;

    @Column(name = "hospital_policy_cutoff_time")
    private LocalTime cutoffTime;

    @Column(name = "hospital_policy_max_guardian_num")
    @Min(value = 0, message = "최대 보호자 수는 0 이상이어야 합니다.")
    private Long maxGuardianNum;

    public void updatePolicyInfo(HospitalPolicyInfoRequest request) {
        this.reserveDayOffset = request.reserveDayOffset();
        this.cutoffTime = request.cutoffTime();
        this.maxGuardianNum = request.maxGuardianNum();
    }
}
