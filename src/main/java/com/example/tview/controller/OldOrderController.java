package com.example.tview.controller;

import com.example.tview.model.CartRoom;
import com.example.tview.model.User;
import com.example.tview.service.CartRoomService;
import com.example.tview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OldOrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartRoomService cartRoomService;

    @GetMapping(value = "/partner/order/old")
    public ModelAndView newOrder(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        List<CartRoom> carts = cartRoomService.cartRoomRepository.findByRoomHotelUserId(user.getId());
        List<CartRoom> carts1 = new ArrayList<>();
        for(CartRoom cartRoom: carts){
            if(cartRoom.isCompleted()){
                carts1.add(cartRoom);
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carts", carts1);
        modelAndView.setViewName("old-order");
        return modelAndView;
    }
}
