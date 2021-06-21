package com.example.tview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TourController {
    @GetMapping(value = "/tour")
    public ModelAndView tour(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("tour");
        return modelAndView;
    }
}
