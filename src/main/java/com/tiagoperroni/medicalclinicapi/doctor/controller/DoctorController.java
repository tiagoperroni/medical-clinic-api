package com.tiagoperroni.medicalclinicapi.doctor.controller;

import com.tiagoperroni.medicalclinicapi.doctor.model.Doctor;
import com.tiagoperroni.medicalclinicapi.doctor.service.DoctorServiceImpl;
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
    private DoctorServiceImpl doctorService;

    @GetMapping
    public ResponseEntity<List<Doctor>> getAll() {
        return new ResponseEntity<>(this.doctorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.doctorService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Doctor doctor) {
        this.doctorService.save(doctor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable("id") Long id, @RequestBody Doctor doctor) {
        this.doctorService.update(id, doctor);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
