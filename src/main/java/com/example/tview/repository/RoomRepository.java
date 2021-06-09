package com.example.tview.repository;

import com.example.tview.model.EvaluateHotel;
import com.example.tview.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelId(Integer HotelId);
    Room findById(Integer id);
    Room save(Room room);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET active = 0 WHERE room_id=?1" , nativeQuery = true)
    int disable(Integer id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET active = 1 WHERE room_id=?1" , nativeQuery = true)
    int able(Integer id);


    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET name = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateName(Integer id, String name);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET description = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateDescription(Integer id, String description);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET cost = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateCost(Integer id, double cost);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET img1 = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateImg1(Integer id, String img1);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET img2 = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateImg2(Integer id, String img2);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET img3 = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateImg3(Integer id, String img3);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET img4 = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateImg4(Integer id, String img4);

    @Modifying
    @Transactional
    @Query(value ="UPDATE room SET mount_of_people = ?2 WHERE room_id=?1" , nativeQuery = true)
    int updateNum(Integer id, Integer num);
}
