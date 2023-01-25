package com.tiagoperroni.medicalclinicapi.room.repository;

import com.tiagoperroni.medicalclinicapi.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
