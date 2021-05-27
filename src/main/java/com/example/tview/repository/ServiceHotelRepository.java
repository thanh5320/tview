package com.example.tview.repository;

import com.example.tview.model.ServiceHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceHotelRepository extends JpaRepository<ServiceHotel, Long> {
}
