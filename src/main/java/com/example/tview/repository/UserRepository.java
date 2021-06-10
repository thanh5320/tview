package com.example.tview.repository;

import com.example.tview.model.City;
import com.example.tview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(Integer id);
    User findUserByUserName( String name);
    User save(User user);

    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET name = ?2 WHERE user_id=?1" , nativeQuery = true)
    void updateName(Integer id,  String name);

    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET email = ?2 WHERE user_id=?1" , nativeQuery = true)
    void updateEmail(Integer id,  String email);

    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET address = ?2 WHERE user_id=?1" , nativeQuery = true)
    void updateAddress(Integer id,  String address);

    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET phone = ?2 WHERE user_id=?1" , nativeQuery = true)
    void updatePhone(Integer id,  String phone);

    @Query(value ="select * from user where type=?1" , nativeQuery = true)
    List<User> queryByType(String phone);

    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET active = 0 WHERE user_id=?1" , nativeQuery = true)
    int lock(Integer id);

    @Modifying
    @Transactional
    @Query(value ="UPDATE user SET active = 1 WHERE user_id=?1" , nativeQuery = true)
    int unlock(Integer id);

}
