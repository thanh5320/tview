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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HotelIdController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private EvaluateHotelService evaluateHotelService;
    private Room room;

    @GetMapping(value = "/hotel-id")
    public ModelAndView homeId(@RequestParam(value = "id", required = false) Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Hotel hotel = hotelService.hotelRepository.findById(id);
        modelAndView.addObject("hotel", hotel);
        List<Room> rooms = roomService.roomRepository.findByHotelIdAndActive(id, true);
        modelAndView.addObject("rooms", rooms);
        double rank=0;
        List<EvaluateHotel> evaluateHotels = evaluateHotelService.evaluateHotelRepository.findByHotelId(id);
        modelAndView.addObject("evas", evaluateHotels);
        int os=0;
        int ts1=0;
        int ts2=0;
        int fs1=0;
        int fs2=0;

        for(EvaluateHotel evaluateHotel: evaluateHotels){
            rank+=evaluateHotel.getStar();
            if(evaluateHotel.getStar()==1){
                os+=1;
            }
            else if(evaluateHotel.getStar()==2)
            {
                ts1+=1;
            }
            else if(evaluateHotel.getStar()==3)
            {
                ts2+=1;
            }
            else if(evaluateHotel.getStar()==4)
            {
                fs1+=1;
            }
            else if(evaluateHotel.getStar()==5)
            {
                fs2+=1;
            }
        }
        modelAndView.addObject("os", os);
        modelAndView.addObject("ts1", ts1);
        modelAndView.addObject("ts2", ts2);
        modelAndView.addObject("fs1", fs1);
        modelAndView.addObject("fs2", fs2);
        rank=rank/evaluateHotels.size();
        rank=Math.round(rank * 10) / 10;
        modelAndView.addObject("rank", rank);
        modelAndView.setViewName("hotel-id");
        return modelAndView;
    }
}
