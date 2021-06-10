package com.example.tview.controller;

import com.example.tview.model.EvaluateHotel;
import com.example.tview.model.Hotel;
import com.example.tview.model.Room;
import com.example.tview.service.EvaluateHotelService;
import com.example.tview.service.HotelService;
import com.example.tview.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private EvaluateHotelService evaluateHotelService;

    @GetMapping(value = {"/home", "/"})
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        List<Hotel> hotelList = new ArrayList<>();
        List<Hotel> hotels = hotelService.hotelRepository.findByActive(true);
        for(Hotel hotel : hotels){
            int s = hotel.getStar();
            if(s>=4){
                hotel.setStar(s);
                List<Room> rooms = roomService.roomRepository.findByHotelId(hotel.getId());
                int cost=0;
                for(Room room : rooms){
                    cost=cost<(int)room.getCost() ? cost:(int)room.getCost();
                }
                hotel.setCost(cost);
                hotelList.add(hotel);
            }
        }
        modelAndView.addObject("hotels", hotelList);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
