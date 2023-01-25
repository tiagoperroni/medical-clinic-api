package com.tiagoperroni.medicalclinicapi.doctor.repository;

import com.tiagoperroni.medicalclinicapi.doctor.model.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository {

    List<Doctor> getAll();

    Doctor findById(Long id);

    void save(Doctor doctor);

    void update(Long id, Doctor doctor);

    void delete(Long id);


}
