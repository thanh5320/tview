package com.example.tview.repository;

import com.example.tview.model.EvaluateHotel;
import com.example.tview.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Integer HotelId);
}
