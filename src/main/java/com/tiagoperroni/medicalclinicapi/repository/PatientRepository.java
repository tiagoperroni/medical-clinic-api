package com.tiagoperroni.medicalclinicapi.repository;

import com.tiagoperroni.medicalclinicapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
