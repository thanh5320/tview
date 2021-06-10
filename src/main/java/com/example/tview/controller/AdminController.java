package com.example.tview.controller;

import com.example.tview.model.FindUserForm;
import com.example.tview.model.HotelForm;
import com.example.tview.model.User;
import com.example.tview.service.NotificationService;
import com.example.tview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/admin/account/partner")
    public ModelAndView partner(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.userRepository.queryByType("partner");
        modelAndView.addObject("partners", users);
        FindUserForm findUserForm = new FindUserForm();
        modelAndView.addObject("findUser", findUserForm);
        modelAndView.setViewName("admin-partner");
        return modelAndView;
    }

    @PostMapping(value = "/admin/account/partner")
    public ModelAndView findPartner(@Valid FindUserForm findUserForm) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.userRepository.queryByType("partner");
        List<User> users1 = new ArrayList<>();
        for(User user : users){
            if((findUserForm.getUserName().equals("") || findUserForm.getUserName().equals(user.getUserName()))&&
                    (findUserForm.getName().equals("") || findUserForm.getName().equals(user.getName())) &&
                    (findUserForm.getEmail().equals("") || findUserForm.getEmail().equals(user.getEmail()))&&
                    (findUserForm.getPhone().equals("") || findUserForm.getPhone().equals(user.getPhone()))){
                users1.add(user);
            }
        }
        modelAndView.addObject("partners", users1);
        modelAndView.addObject("findUser", findUserForm);
        modelAndView.setViewName("admin-partner");
        return modelAndView;
    }


        @PutMapping(value = "/admin/lock/user/{id}")
    public ResponseEntity<String> lockUser(@PathVariable Integer id){
        int rs = userService.userRepository.lock(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping(value = "/admin/unlock/user/{id}")
    public ResponseEntity<String> unlockUser(@PathVariable Integer id){
        int rs = userService.userRepository.unlock(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping(value = "/admin/send/notification/{id}")
    public ResponseEntity<String> sendNotifi(@RequestBody String notification, @PathVariable Integer id){
        User user = userService.userRepository.findById(id);
        notificationService.createNotification(user , notification);
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    @GetMapping(value = "/admin/account/user")
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.userRepository.queryByType("customer");
        modelAndView.addObject("users", users);
        FindUserForm findUserForm = new FindUserForm();
        modelAndView.addObject("findUser", findUserForm);
        modelAndView.setViewName("admin-user");
        return modelAndView;
    }

    @PostMapping(value = "/admin/account/user")
    public ModelAndView findUser(@Valid FindUserForm findUserForm) {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.userRepository.queryByType("customer");
        List<User> users1 = new ArrayList<>();
        for(User user : users){
            if((findUserForm.getUserName().equals("") || findUserForm.getUserName().equals(user.getUserName()))&&
                    (findUserForm.getName().equals("") || findUserForm.getName().equals(user.getName())) &&
                    (findUserForm.getEmail().equals("") || findUserForm.getEmail().equals(user.getEmail()))&&
                    (findUserForm.getPhone().equals("") || findUserForm.getPhone().equals(user.getPhone()))){
                users1.add(user);
            }
        }
        modelAndView.addObject("users", users1);
        modelAndView.addObject("findUser", findUserForm);
        modelAndView.setViewName("admin-user");
        return modelAndView;
    }
}
