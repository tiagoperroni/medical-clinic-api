package com.tiagoperroni.medicalclinicapi.service;

import com.tiagoperroni.medicalclinicapi.model.Patient;
import com.tiagoperroni.medicalclinicapi.repository.PatientRepository;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientService patientService;

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void getAllPatientsTest() {
        when(this.patientRepository.findAll()).thenReturn(List.of(this.getPatient()));

        List<Patient> response = this.patientService.getPatients();

        assertEquals(1, response.size());
        assertTrue(response.get(0).getName().equals("Tiago Perroni"));

    }

    @Test
    public void findPatientByIdTest() {
        when(this.patientRepository.findById(anyLong())).thenReturn(Optional.of(this.getPatient()));

        var patient = this.patientService.findPatientById(1l);

        assertEquals("897.553.700-56", patient.getCpf());
        assertNotNull(patient);
    }

    @Test
    public void  saveNewPatientTest() {
        when(this.patientRepository.save(any())).thenReturn(this.getPatient());

        var response = this.patientService.savePatient(
                new Patient(1l, "Tiago Perroni", "897.553.700-56", 36, null));

        assertNotNull(response);
        assertEquals(36, response.getAge(), "Obtain the age");
    }

    @Test
    public void updatePatientByIdTest() {
        var updatePatient = new Patient(2l, "Maria Silva", "081.923.600-40", 42, null);

        when(this.patientRepository.save(any())).thenReturn(updatePatient);
        when(this.patientRepository.findById(anyLong())).thenReturn(Optional.of(this.getPatient()));

        var response = this.patientService.updatePatient(1l, updatePatient);

        assertNotNull(response);
        assertEquals("Maria Silva", response.getName());
    }

    @Test
    public void deletePatientById() {
        doNothing().when(this.patientRepository).delete(any());
        when(this.patientRepository.findById(anyLong())).thenReturn(Optional.of(this.getPatient()));

        this.patientService.deletePatient(1l);
        verify(this.patientRepository, times(1)).delete(any());
    }

    private Patient getPatient() {
        return new Patient(1l, "Tiago Perroni", "897.553.700-56", 36, null);
    }
}
