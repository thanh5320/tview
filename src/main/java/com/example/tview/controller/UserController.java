package com.example.tview.controller;

import com.example.tview.model.ChangePass;
import com.example.tview.model.Image;
import com.example.tview.model.UpdateUser;
import com.example.tview.model.User;
import com.example.tview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class UserController {
    private String fileUpload="C:\\Users\\NGUYEN VAN THANH\\Desktop\\THANH\\tview\\tview\\src\\main\\resources\\static\\images\\user\\";

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/user")
    public ModelAndView user(){
        ModelAndView modelAndView =new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(userService.userRepository.findUserByUserName(auth.getName())!=null) {
            User user1 = userService.userRepository.findUserByUserName(auth.getName());
            UpdateUser user = new UpdateUser(user1);
            modelAndView.addObject("user", user);
        }
        modelAndView.setViewName("user");
        return modelAndView;
    }

    @PostMapping(value = "/user")
    public String updateUser(@Valid UpdateUser user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.userRepository.findUserByUserName(auth.getName());
        userService.userRepository.updateName(user1.getId(), user.getName());
        userService.userRepository.updatePhone(user1.getId(), user.getPhone());
        userService.userRepository.updateEmail(user1.getId(), user.getEmail());
        userService.userRepository.updateAddress(user1.getId(), user.getAddress());
        MultipartFile multipartFile = user.getFileImg();
        String fileName = multipartFile.getOriginalFilename();
        try {
            if(!("images/user/"+fileName).equals(user1.getImg())){
                FileCopyUtils.copy(user.getFileImg().getBytes(), new File(this.fileUpload + fileName));
                userService.userRepository.updateImg(user1.getId(), "images/user/"+fileName);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        return "redirect:/user";
    }

    @GetMapping(value = "/change-password")
    public ModelAndView getChangePassword(){
        ModelAndView modelAndView = new ModelAndView();
        ChangePass changePass = new ChangePass();
        modelAndView.addObject("changePass", changePass);
        modelAndView.setViewName("change_password");
        return modelAndView;
    }

    @PostMapping(value = "/change-password")
    public ModelAndView changePassword(@Valid ChangePass changePass){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        if(bCryptPasswordEncoder.encode(changePass.getOldPass()).equals(user.getPassword())){
            modelAndView.addObject("mess" , "Mật khẩu không chính xác!");
            System.out.println(bCryptPasswordEncoder.encode(changePass.getOldPass()));
            System.out.println(user.getPassword());
        }else {
            modelAndView.addObject("mess", "Đổi mật khẩu thành công!");
            userService.userRepository.updatePass(user.getId(), bCryptPasswordEncoder.encode(changePass.getNewPass()));
        }
        modelAndView.addObject("changePass", new ChangePass());
        modelAndView.setViewName("change_password");
        return modelAndView;
    }
}
