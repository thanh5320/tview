package com.example.tview.repository;

import com.example.tview.model.CartRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRoomRepository extends JpaRepository<CartRoom, Long> {
    CartRoom save(CartRoom cartRoom);
    List<CartRoom> findByUserId(Integer id);
    CartRoom findById(Integer id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE cart_room SET eva_id = ?2 WHERE cart_id=?1" , nativeQuery = true)
    void updateIdEva(Integer id, Integer id_eva);

    List<CartRoom> findByRoomHotelUserId(Integer id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE cart_room SET completed = ?2 WHERE cart_id=?1" , nativeQuery = true)
    void updatecompleted(Integer id, boolean b);

}
