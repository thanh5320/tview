package com.example.tview.controller;

import com.example.tview.model.Notification;
import com.example.tview.model.User;
import com.example.tview.service.NotificationService;
import com.example.tview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NotificationController {
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @GetMapping(value = "/notification")
    public ModelAndView notification(){
        ModelAndView modelAndView =new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        List<Notification> notifications = notificationService.notificationRepository.findByUserId(user.getId());
        modelAndView.addObject("notifications", notifications);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("notification");
        return modelAndView;
    }

    @GetMapping(value = "/delete-notification")
    public void deleteNotification(@RequestParam(value = "id", required = false) Integer id){
       if(id==null) return;
       notificationService.notificationRepository.delete(id);
       return;
    }
}
