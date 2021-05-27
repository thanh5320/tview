package com.example.tview.service;

import com.example.tview.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    public CityRepository cityRepository;
}
