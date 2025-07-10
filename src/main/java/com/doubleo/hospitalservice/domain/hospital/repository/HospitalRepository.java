package com.doubleo.hospitalservice.domain.hospital.repository;

import com.doubleo.hospitalservice.domain.hospital.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {}
