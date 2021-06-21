package com.example.tview.controller;

import com.example.tview.model.CartRoom;
import com.example.tview.model.EvaluateHotel;
import com.example.tview.model.User;
import com.example.tview.service.CartRoomService;
import com.example.tview.service.EvaluateHotelService;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartRoomService cartRoomService;
    @Autowired
    private EvaluateHotelService evaluateHotelService;

    @GetMapping(value = "/cart")
    public ModelAndView cart(){
        ModelAndView modelAndView =new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        List<CartRoom> cartRooms = cartRoomService.cartRoomRepository.findByUserId(user.getId());
        modelAndView.addObject("carts" , cartRooms);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @GetMapping(value = "/evaluate/{id}")
    public ResponseEntity<String> ableHotel(@PathVariable Integer id) {
        CartRoom cartRoom = cartRoomService.cartRoomRepository.findById(id);
        EvaluateHotel evaluateHotel = evaluateHotelService.evaluateHotelRepository.findById(cartRoom.getEvaId());
        String body="";
        if(cartRoom.getEvaId()==0){
            body = "null";
        }else {
            body+=evaluateHotel.getStar()+"\n"+evaluateHotel.getComment();
        }

        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @GetMapping(value = "post/evaluate/{id}/{st}/{cmt}")
    public ResponseEntity<String> postCmt(@PathVariable Integer id, @PathVariable Integer st, @PathVariable String cmt) {
        CartRoom cartRoom = cartRoomService.cartRoomRepository.findById(id);
        if(cartRoom.getEvaId()==0){
            EvaluateHotel evaluateHotel = new EvaluateHotel();
            evaluateHotel.setDate(new Date());
            evaluateHotel.setComment(cmt);
            evaluateHotel.setStar(st);
            evaluateHotel.setHotel(cartRoom.getRoom().getHotel());
            evaluateHotel.setUser(cartRoom.getUser());
            if(st==1){
                evaluateHotel.setTitle("Kém");
            }
            else if(st==2){
                evaluateHotel.setTitle("Trung bình");
            }
            else if(st==3){
                evaluateHotel.setTitle("Tốt");
            }else if(st==4){
                evaluateHotel.setTitle("Xuất sắc");
            }else if(st==5){
                evaluateHotel.setTitle("Tuyệt vời");
            }


            evaluateHotel = evaluateHotelService.evaluateHotelRepository.save(evaluateHotel);
            cartRoomService.cartRoomRepository.updateIdEva(cartRoom.getId(), evaluateHotel.getId());
        }else{
            EvaluateHotel evaluateHotel = evaluateHotelService.evaluateHotelRepository.findById(cartRoom.getEvaId());
            evaluateHotelService.evaluateHotelRepository.updateCmt(evaluateHotel.getId(), cmt);
            evaluateHotelService.evaluateHotelRepository.updateStar(evaluateHotel.getId(), st);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
