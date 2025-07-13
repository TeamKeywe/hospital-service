package com.doubleo.hospitalservice.domain.hospital.domain;

import com.doubleo.hospitalservice.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        name = "hospital",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "UniqueTenantId",
                    columnNames = {"tenant_id"})
        })
public class Hospital extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    @Column(name = "hospital_name", nullable = false)
    private String hospitalName;
}
