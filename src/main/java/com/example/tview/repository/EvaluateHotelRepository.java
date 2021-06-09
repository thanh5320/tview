package com.example.tview.repository;

import com.example.tview.model.EvaluateHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EvaluateHotelRepository extends JpaRepository<EvaluateHotel, Long> {
    List<EvaluateHotel> findByHotelId(Integer HotelId);
    EvaluateHotel save(EvaluateHotel evaluateHotel);
    EvaluateHotel findById(Integer id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE evaluate_hotel SET comment = ?2 WHERE eva_id=?1" , nativeQuery = true)
    void updateCmt(Integer id, String cmt);

    @Modifying
    @Transactional
    @Query(value ="UPDATE evaluate_hotel SET star = ?2 WHERE eva_id=?1" , nativeQuery = true)
    void updateStar(Integer id, Integer star);

}
