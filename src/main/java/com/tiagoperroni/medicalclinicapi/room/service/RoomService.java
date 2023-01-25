package com.tiagoperroni.medicalclinicapi.room.service;

import com.tiagoperroni.medicalclinicapi.exceptions.EntityNotFoundException;
import com.tiagoperroni.medicalclinicapi.room.model.Room;
import com.tiagoperroni.medicalclinicapi.room.repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAll() {
        return this.roomRepository.findAll();
    }

    public Room findById(Long id) {
        return this.roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("A sala de id %s, não foi encontrada.", id)));
    }

    public Room save(Room room) {
        room.setRegisterDate(LocalDate.now());
        return this.roomRepository.save(room);
    }

    public Room update(Long id, Room room) {
        var oldRoom = this.findById(id);
        BeanUtils.copyProperties(room, oldRoom, "id", "registerDate");
        return this.roomRepository.save(oldRoom);
    }

    public void delete(Long id) {
        var patient = this.findById(id);
        this.roomRepository.delete(patient);
    }
}
