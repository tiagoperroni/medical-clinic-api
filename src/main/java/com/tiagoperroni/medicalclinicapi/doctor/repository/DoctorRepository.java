package com.tiagoperroni.medicalclinicapi.doctor.repository;

import com.tiagoperroni.medicalclinicapi.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
