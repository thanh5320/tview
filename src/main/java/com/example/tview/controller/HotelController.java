package com.example.tview.controller;

import com.example.tview.model.Hotel;
import com.example.tview.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/hotel")
    public ModelAndView hotel(@RequestParam(value = "city", required = false) String city,
                              @RequestParam(value = "hotel", required = false) String name,
                              @RequestParam(value = "star", required = false) Integer star){
        ModelAndView modelAndView = new ModelAndView();

        List<Hotel> hotels = hotelService.hotelRepository.findByActive(true);
        List<Hotel> hotels1 = new ArrayList<>();

        for (Hotel hotel : hotels){
            if((city ==null || hotel.getCity().getName().contains(city)) &&
                    (name==null || hotel.getName().contains(name)) &&
                    (star==null || hotel.getStar()==star)){
                hotels1.add(hotel);
            }
        }

        modelAndView.addObject("hotels", hotels1);
        modelAndView.addObject("city", city);
        modelAndView.addObject("hotel", name);
        modelAndView.addObject("star", star);
        modelAndView.setViewName("hotel");
        return modelAndView;
    }
}
