package com.doubleo.hospitalservice.domain.area.domain;

import com.doubleo.hospitalservice.domain.building.domain.Building;
import com.doubleo.hospitalservice.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        name = "area",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "UniqueTenantId",
                    columnNames = {"tenant_id", "area_id"})
        })
public class Area extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column(name = "area_name", nullable = false)
    private String areaName;

    @Column(name = "area_code", nullable = false)
    private String areaCode;
}
