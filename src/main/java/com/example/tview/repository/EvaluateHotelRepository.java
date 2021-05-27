package com.example.tview.repository;

import com.example.tview.model.EvaluateHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateHotelRepository extends JpaRepository<EvaluateHotel, Long> {
    List<EvaluateHotel> findByHotelId(Integer HotelId);


}
