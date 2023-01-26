package com.tiagoperroni.medicalclinicapi.Appointment.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class AppointmentRequestDTO {

    private Long doctorId;

    private Long roomId;

    private Long patientId;

    private LocalDateTime appointmentDate;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentRequestDTO that = (AppointmentRequestDTO) o;
        return Objects.equals(doctorId, that.doctorId) && Objects.equals(roomId, that.roomId) && Objects.equals(patientId, that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, roomId, patientId);
    }
}
