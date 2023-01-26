package com.tiagoperroni.medicalclinicapi.room.controller;

import com.tiagoperroni.medicalclinicapi.patient.model.Patient;
import com.tiagoperroni.medicalclinicapi.patient.service.PatientService;
import com.tiagoperroni.medicalclinicapi.room.model.Room;
import com.tiagoperroni.medicalclinicapi.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAll() {
        return new ResponseEntity<>(this.roomService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.roomService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Room> save(@RequestBody Room room) {
        return new ResponseEntity<>(this.roomService.save(room), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> update(@PathVariable("id") Long id, @RequestBody Room room) {
        return new ResponseEntity<>(this.roomService.update(id, room), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.roomService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
