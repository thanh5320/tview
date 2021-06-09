package com.example.tview.controller;

import com.example.tview.model.Notification;
import com.example.tview.model.User;
import com.example.tview.service.NotificationService;
import com.example.tview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
public class SignupController {
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping(value="/signup")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping(value = "/signup")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.userRepository.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult
                    .rejectValue("userName", "error.user",
                            "*Username is already taken");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            User user1 = userService.saveUser(user);
            notificationService.createNotification(user, "Chào bạn! Bạn vừa đăng ký tài khoản thành công!");
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("signup");
        }
        return modelAndView;
    }
}
