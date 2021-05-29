package com.example.tview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping(value="/login")
    public ModelAndView login(@RequestParam(value="error", required = false, defaultValue = "false") Boolean error){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        if(error==true) {
            modelAndView.addObject("loginFalse", true);
        }
        else{
            modelAndView.addObject("loginFalse", false);
        }
        return modelAndView;
    }


    /*
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(userService.findUserByUserName(auth.getName())!=null){
            User user=new User();
            user = userService.findUserByUserName(auth.getName());
            modelAndView.addObject("logined",true);
            modelAndView.addObject("user",user);
        }else
        {
            modelAndView.addObject("logined",false);
        }

     */
}
