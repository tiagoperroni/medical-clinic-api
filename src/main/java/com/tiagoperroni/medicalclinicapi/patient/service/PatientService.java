package com.tiagoperroni.medicalclinicapi.patient.service;

import com.tiagoperroni.medicalclinicapi.exceptions.EntityMissDataException;
import com.tiagoperroni.medicalclinicapi.exceptions.EntityNotFoundException;
import com.tiagoperroni.medicalclinicapi.patient.model.Patient;
import com.tiagoperroni.medicalclinicapi.patient.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getPatients() {
        return this.patientRepository.findAll();
    }

    public Patient findPatientById(Long id) {
        return this.patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("O paciente de id %s, não foi encontrado.", id)));
    }

    public Patient savePatient(Patient patient) {
        this.patientDataVerify(patient);
        patient.setRegisterDate(LocalDate.now());
        return this.patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patient) {
        var oldPatient = this.findPatientById(id);
        BeanUtils.copyProperties(patient, oldPatient, "id", "registerDate");
        return this.patientRepository.save(oldPatient);
    }

    public void deletePatient(Long id) {
        var patient = this.findPatientById(id);
        this.patientRepository.delete(patient);
    }

    /**
     * Verifies if all data was informed
     * */
    private void patientDataVerify(Patient patient) {
        var patients = this.getPatients();

        if (patients.stream().anyMatch(cpf -> cpf.getCpf().equals(patient.getCpf()))) {
            throw new EntityMissDataException("O CPF informado já está em uso no sistema.");
        }
    }
}
