package com.tiagoperroni.medicalclinicapi.Appointment.controller;

import com.tiagoperroni.medicalclinicapi.Appointment.dto.AppointmentRequestDTO;
import com.tiagoperroni.medicalclinicapi.Appointment.dto.AppointmentResponseDTO;
import com.tiagoperroni.medicalclinicapi.Appointment.model.Appointment;
import com.tiagoperroni.medicalclinicapi.Appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin("*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAll() {
        return new ResponseEntity<>(this.appointmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.appointmentService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Appointment> save(@RequestBody AppointmentRequestDTO appointmentRequestDTO) {
        return new ResponseEntity<>(this.appointmentService.save(appointmentRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable("id") Long id, @RequestBody AppointmentRequestDTO requestDTO) {
        return new ResponseEntity<>(this.appointmentService.update(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
