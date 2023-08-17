package com.example.blogspring.repository;


import com.example.blogspring.entity.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByloginId(String loginId);

    Optional<Patient> findByLoginId(String loginId);
}
