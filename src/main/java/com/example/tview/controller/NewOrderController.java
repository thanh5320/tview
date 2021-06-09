package com.example.tview.controller;

import com.example.tview.model.CartRoom;
import com.example.tview.model.User;
import com.example.tview.service.CartRoomService;
import com.example.tview.service.NotificationService;
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
public class NewOrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartRoomService cartRoomService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/partner/order/new")
    public ModelAndView newOrder(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        List<CartRoom> carts = cartRoomService.cartRoomRepository.findByRoomHotelUserId(user.getId());
        List<CartRoom> carts1 = new ArrayList<>();
        for(CartRoom cartRoom: carts){
            if(!cartRoom.isCompleted()){
                carts1.add(cartRoom);
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carts", carts1);
        modelAndView.setViewName("new-order");
        return modelAndView;
    }

    @PutMapping(value = "/completed/cart/{id}")
    public ResponseEntity<String> completed(@PathVariable Integer id){
        cartRoomService.cartRoomRepository.updatecompleted(id, true);
        CartRoom cartRoom = cartRoomService.cartRoomRepository.findById(id);
        notificationService.createNotification(cartRoom.getUser(), "Bạn vừa trải nghiệm dịch vụ của chúng tôi. Xin hãy đánh giá để chúng tôi cải thiện hơn!");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
