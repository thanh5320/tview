package com.example.tview.repository;

import com.example.tview.model.City;
import com.example.tview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(Integer id);

}
