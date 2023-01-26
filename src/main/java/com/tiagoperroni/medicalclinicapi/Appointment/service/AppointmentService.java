package com.tiagoperroni.medicalclinicapi.Appointment.service;

import com.tiagoperroni.medicalclinicapi.Appointment.dto.AppointmentRequestDTO;
import com.tiagoperroni.medicalclinicapi.Appointment.dto.AppointmentResponseDTO;
import com.tiagoperroni.medicalclinicapi.Appointment.model.Appointment;
import com.tiagoperroni.medicalclinicapi.Appointment.repository.AppointmentRepository;
import com.tiagoperroni.medicalclinicapi.doctor.service.DoctorServiceImpl;
import com.tiagoperroni.medicalclinicapi.exceptions.EntityNotFoundException;
import com.tiagoperroni.medicalclinicapi.patient.service.PatientService;
import com.tiagoperroni.medicalclinicapi.room.service.RoomService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorServiceImpl doctorService;
    private final RoomService roomService;
    private final PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorServiceImpl doctorService, RoomService roomService, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.roomService = roomService;
        this.patientService = patientService;
    }

    public List<AppointmentResponseDTO> getAll() {
        return this.appointmentRepository.findAll()
                .stream().map(obj -> AppointmentResponseDTO.convertAppointment(obj)).collect(Collectors.toList());
    }

    public AppointmentResponseDTO findById(Long id) {
        var appointment = this.appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("A consulta de id %s, não foi encontrada.", id)));
        return AppointmentResponseDTO.convertAppointment(appointment);
    }

    public Appointment save(AppointmentRequestDTO appointmentDTO) {
        var doctor = this.doctorService.findById(appointmentDTO.getDoctorId());
        var room = this.roomService.findById(appointmentDTO.getRoomId());
        var patient = this.patientService.findPatientById(appointmentDTO.getPatientId());
        var appointment = new Appointment(null, doctor, room, patient, appointmentDTO.getAppointmentDate());
        return this.appointmentRepository.save(appointment);
    }

    public Appointment update(Long id, AppointmentRequestDTO appointmentDTO) {

        var doctor = this.doctorService.findById(appointmentDTO.getDoctorId());
        var room = this.roomService.findById(appointmentDTO.getRoomId());
        var patient = this.patientService.findPatientById(appointmentDTO.getPatientId());
        var oldAppointment = this.appointmentRepository.findById(id).orElse(null);

        oldAppointment.setDoctor(doctor);
        oldAppointment.setRoom(room);
        oldAppointment.setPatient(patient);
        oldAppointment.setDate(appointmentDTO.getAppointmentDate());

        return this.appointmentRepository.save(oldAppointment);
    }

    public void delete(Long id) {
        var patient = this.appointmentRepository.findById(id).orElse(null);
        this.appointmentRepository.delete(patient);
    }
}
