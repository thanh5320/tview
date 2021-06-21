package com.example.tview.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home";
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
