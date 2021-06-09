package com.example.tview.controller;

import com.example.tview.model.CartRoom;
import com.example.tview.model.Hotel;
import com.example.tview.model.Room;
import com.example.tview.model.User;
import com.example.tview.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomPaymentController {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;

    @Autowired
    CartRoomService cartRoomService;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @GetMapping(value = "/room-payment")
    public ModelAndView roomPayment(@RequestParam(value = "room-id", required = false) Integer roomId,
                                    @RequestParam(value = "hotel-id", required = false) Integer hotelId){
        ModelAndView modelAndView = new ModelAndView();
        Room room = roomService.roomRepository.findById(roomId);
        Hotel hotel = hotelService.hotelRepository.findById(hotelId);
        modelAndView.addObject("room", room);
        modelAndView.addObject("hotel", hotel);
        modelAndView.setViewName("room-payment");
        return modelAndView;
    }

    @GetMapping(value = "/add-cart-room")
    public String addCartRoom(@RequestParam(value = "id", required = false) Integer roomId){
        cartRoomService.saveCart(roomId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        notificationService.createNotification(user, "Bạn vừa đặt phòng thành công!");
        return "redirect:/cart";
    }
}
