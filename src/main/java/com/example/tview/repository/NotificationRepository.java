package com.example.tview.repository;

import com.example.tview.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Integer id);

    @Modifying
    @Transactional
    @Query(value ="DELETE FROM notification WHERE no_id=?1" , nativeQuery = true)
    void delete(Integer id);
}
