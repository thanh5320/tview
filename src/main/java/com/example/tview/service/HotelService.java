package com.example.tview.service;

import com.example.tview.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired
    public HotelRepository hotelRepository;

    public boolean disable(Integer id){
        int x = hotelRepository.disable(id);
        if(x!=0) return true;
        return false;
    }
    public boolean able(Integer id){
        int x = hotelRepository.able(id);
        if(x!=0) return true;
        return false;
    }

}
