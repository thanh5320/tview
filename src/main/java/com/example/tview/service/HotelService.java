package com.example.tview.service;

import com.example.tview.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
    @Autowired
    public HotelRepository hotelRepository;
}
