package com.doubleo.hospitalservice.domain.building.domain;

import com.doubleo.hospitalservice.domain.area.domain.Area;
import com.doubleo.hospitalservice.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
@Table(
        name = "building",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "UniqueTenantId",
                    columnNames = {"tenant_id", "building_id"})
        })
public class Building extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long buildingId;

    @Column(name = "building_name", nullable = false)
    private String buildingName;

    @Column(name = "building_code", nullable = false)
    private String buildingCode;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Area> areas;
}
