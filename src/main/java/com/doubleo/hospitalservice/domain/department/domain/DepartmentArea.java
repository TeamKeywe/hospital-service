package com.doubleo.hospitalservice.domain.department.domain;

import com.doubleo.hospitalservice.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(
        name = "department_area",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"tenant_id", "department_id", "area_id"})
        })
public class DepartmentArea extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_area_id")
    private Long id;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column(name = "area_id", nullable = false)
    private Long areaId;
}
