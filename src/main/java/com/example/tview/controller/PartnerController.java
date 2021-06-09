package com.example.tview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartnerController {

    @GetMapping(value = "/partner")
    public ModelAndView partner(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("partner");
        return modelAndView;
    }
}
