package com.example.tview.service;

import com.example.tview.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    public RoomRepository roomRepository;
}
