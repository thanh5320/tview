package com.example.tview.service;

import com.example.tview.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    public RoomRepository roomRepository;

    public boolean disable(Integer id){
        int x = roomRepository.disable(id);
        if(x!=0) return true;
        return false;
    }
    public boolean able(Integer id){
        int x = roomRepository.able(id);
        if(x!=0) return true;
        return false;
    }
}
