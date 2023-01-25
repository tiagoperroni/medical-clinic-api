package com.tiagoperroni.medicalclinicapi.doctor.service;

import com.tiagoperroni.medicalclinicapi.doctor.model.Doctor;
import com.tiagoperroni.medicalclinicapi.doctor.repository.DoctorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAll() {
        return this.doctorRepository.findAll();
    }

    public Doctor findById(Long id) {
        return this.doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("O Médico de id %s, não foi encontrado.", id)));
    }

    public Doctor save(Doctor doctor) {
        doctor.setRegisterDate(LocalDate.now());
        return this.doctorRepository.save(doctor);
    }

    public Doctor update(Long id, Doctor doctor) {
        var oldRoom = this.findById(id);
        BeanUtils.copyProperties(doctor, oldRoom, "id", "registerDate");
        return this.doctorRepository.save(oldRoom);
    }

    public void delete(Long id) {
        var patient = this.findById(id);
        this.doctorRepository.delete(patient);
    }
}
