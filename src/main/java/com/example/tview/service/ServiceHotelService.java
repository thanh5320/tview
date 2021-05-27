package com.example.tview.service;

import com.example.tview.repository.ServiceHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHotelService {
    @Autowired
    public ServiceHotelRepository serviceHotelRepository;
}
