package com.example.tview.repository;

import com.example.tview.model.ServiceHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ServiceHotelRepository extends JpaRepository<ServiceHotel, Long> {
    ServiceHotel save(ServiceHotel serviceHotel);

    @Query(value ="select * from service_hotel WHERE wifi=?1 and pool=?2 and gym=?3 and elevator=?4 and restaurant=?5" , nativeQuery = true)
    ServiceHotel findByAllAttr(boolean wifi, boolean pool, boolean gym, boolean elevator, boolean restaurant);
}
