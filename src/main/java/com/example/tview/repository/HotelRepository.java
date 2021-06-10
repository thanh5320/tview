package com.example.tview.repository;

import com.example.tview.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findById(Integer id);
    List<Hotel> findByUserId(Integer id);

    Hotel save(Hotel hotel);

    List<Hotel> findByActive(boolean active);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET active = 0 WHERE hotel_id=?1" , nativeQuery = true)
    int disable(Integer id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET active = 1 WHERE hotel_id=?1" , nativeQuery = true)
    int able(Integer id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET name = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateName(Integer id, String name);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET address = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateAddress(Integer id, String address);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET city_id = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateCityId(Integer id, Integer city_id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET s_h_id = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateServiceHotelId(Integer id, Integer s_h_id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET description = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateDescription(Integer id, String description);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET star = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateStar(Integer id, Integer star);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET avatar = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateAvatar(Integer id, String avatar);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET img1 = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateImg1(Integer id, String img1);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET img2 = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateImg2(Integer id, String img2);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET img3 = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateImg3(Integer id, String img3);

    @Modifying
    @Transactional
    @Query(value ="UPDATE hotel SET img4 = ?2 WHERE hotel_id=?1" , nativeQuery = true)
    int updateImg4(Integer id, String img4);



}
