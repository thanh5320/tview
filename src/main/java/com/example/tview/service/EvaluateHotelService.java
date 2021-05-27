package com.example.tview.service;

import com.example.tview.repository.CityRepository;
import com.example.tview.repository.EvaluateHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateHotelService {
    @Autowired
    public EvaluateHotelRepository evaluateHotelRepository;
}
