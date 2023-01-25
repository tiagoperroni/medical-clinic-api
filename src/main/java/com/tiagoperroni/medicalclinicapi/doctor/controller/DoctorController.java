package com.tiagoperroni.medicalclinicapi.doctor.controller;

import com.tiagoperroni.medicalclinicapi.doctor.model.Doctor;
import com.tiagoperroni.medicalclinicapi.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin("http://localhost:4200/**")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAll() {
        return new ResponseEntity<>(this.doctorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.doctorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Doctor> save(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(this.doctorService.save(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable("id") Long id, @RequestBody Doctor doctor) {
        return new ResponseEntity<>(this.doctorService.update(id, doctor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
