package com.tiagoperroni.medicalclinicapi.Appointment.repository;

import com.tiagoperroni.medicalclinicapi.Appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
