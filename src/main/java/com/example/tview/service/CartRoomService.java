package com.example.tview.service;

import com.example.tview.model.CartRoom;
import com.example.tview.model.EvaluateHotel;
import com.example.tview.model.User;
import com.example.tview.repository.CartRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class CartRoomService {
    @Autowired
    public CartRoomRepository cartRoomRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserService userService;

    public void saveCart(Integer roomId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        CartRoom cartRoom = new CartRoom();
        cartRoom.setDate(new Date());
        cartRoom.setAmount(1);
        cartRoom.setCompleted(false);
        cartRoom.setEvaId(0);
        cartRoom.setRoom(roomService.roomRepository.findById(roomId));
        cartRoom.setUser(user);
        CartRoom cartRoom1 = cartRoomRepository.save(cartRoom);
    }
}
