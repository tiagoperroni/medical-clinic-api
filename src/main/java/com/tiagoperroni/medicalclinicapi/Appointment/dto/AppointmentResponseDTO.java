package com.tiagoperroni.medicalclinicapi.Appointment.dto;


import com.tiagoperroni.medicalclinicapi.Appointment.model.Appointment;

import java.time.LocalDateTime;

public class AppointmentResponseDTO {
    private Long id;

    private String doctorName;

    private String doctorCRM;

    private String patientName;

    private Integer roomNumber;

    private LocalDateTime date;

    public static AppointmentResponseDTO convertAppointment(Appointment appointment) {
        var appointmentDTO = new AppointmentResponseDTO();
        appointmentDTO.setId(appointment.getId());
        appointmentDTO.setDoctorName(appointment.getDoctor().getName());
        appointmentDTO.setDoctorCRM(appointment.getDoctor().getCrm());
        appointmentDTO.setPatientName(appointment.getPatient().getName());
        appointmentDTO.setRoomNumber(appointment.getRoom().getRoomNumber());
        appointmentDTO.setDate(LocalDateTime.now());
        return appointmentDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorCRM() {
        return doctorCRM;
    }

    public void setDoctorCRM(String doctorCRM) {
        this.doctorCRM = doctorCRM;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
