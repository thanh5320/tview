package com.example.tview.controller;

import com.example.tview.model.User;
import com.example.tview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public ModelAndView user(){
        ModelAndView modelAndView =new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(userService.userRepository.findUserByUserName(auth.getName())!=null) {
            User user = new User();
            user = userService.userRepository.findUserByUserName(auth.getName());
            modelAndView.addObject("user", user);
        }
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @PostMapping(value = "/user")
    public ModelAndView updateUser(@Valid User user) {
        userService.userRepository.updateName(user.getId(), user.getName());
        userService.userRepository.updatePhone(user.getId(), user.getPhone());
        userService.userRepository.updateEmail(user.getId(), user.getEmail());
        userService.userRepository.updateAddress(user.getId(), user.getAddress());
        return user();
    }
}
