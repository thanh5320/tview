package com.example.tview.controller;

import com.example.tview.model.*;
import com.example.tview.service.CityService;
import com.example.tview.service.HotelService;
import com.example.tview.service.ServiceHotelService;
import com.example.tview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class HotelManagerController {
    private String fileUpload="C:\\Users\\NGUYEN VAN THANH\\Desktop\\THANH\\tview\\tview\\src\\main\\resources\\static\\images\\hotel\\";
    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ServiceHotelService serviceHotelService;

    @GetMapping(value = "/partner/hotel")
    public ModelAndView hotel(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());
        List<Hotel> hotels = hotelService.hotelRepository.findByUserId(user.getId());
        modelAndView.addObject("hotels", hotels);
        modelAndView.setViewName("hotel-manager");
        return modelAndView;
    }

    @PutMapping(value = "/partner/disable/hotel/{id}")
    public ResponseEntity<Integer> disableHotel(@PathVariable Integer id) {
        boolean isRemoved = hotelService.disable(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/partner/able/hotel/{id}")
    public ResponseEntity<Integer> ableHotel(@PathVariable Integer id) {
        boolean isRemoved = hotelService.able(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/partner/add-hotel")
    public ModelAndView addHotel(){
        ModelAndView modelAndView = new ModelAndView();
        HotelForm hotel = new HotelForm();
        modelAndView.addObject("hotel", hotel);
        modelAndView.setViewName("add-hotel");
        return modelAndView;
    }


    @PostMapping(value = "/partner/add-hotel")
    public ModelAndView createHotel(@Valid HotelForm hotelForm) {

        MultipartFile multipartFile1 = hotelForm.getAvatar();
        String fileName1 = multipartFile1.getOriginalFilename();
        MultipartFile multipartFile2 = hotelForm.getImg1();
        String fileName2 = multipartFile2.getOriginalFilename();
        MultipartFile multipartFile3 = hotelForm.getImg2();
        String fileName3 = multipartFile3.getOriginalFilename();
        MultipartFile multipartFile4 = hotelForm.getImg3();
        String fileName4 = multipartFile4.getOriginalFilename();
        MultipartFile multipartFile5 = hotelForm.getImg4();
        String fileName5 = multipartFile5.getOriginalFilename();

        try {
            FileCopyUtils.copy(hotelForm.getAvatar().getBytes(), new File(this.fileUpload + fileName1));
            FileCopyUtils.copy(hotelForm.getImg1().getBytes(), new File(this.fileUpload + fileName2));
            FileCopyUtils.copy(hotelForm.getImg2().getBytes(), new File(this.fileUpload + fileName3));
            FileCopyUtils.copy(hotelForm.getImg3().getBytes(), new File(this.fileUpload + fileName4));
            FileCopyUtils.copy(hotelForm.getImg4().getBytes(), new File(this.fileUpload + fileName5));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.userRepository.findUserByUserName(auth.getName());

        City city = cityService.cityRepository.findByName(hotelForm.getCity());

        ServiceHotel serviceHotel = serviceHotelService.serviceHotelRepository.findByAllAttr(
                hotelForm.isWifi(), hotelForm.isPool(),
                hotelForm.isGym(), hotelForm.isElevator(),hotelForm.isRestaurant()
       );

        Hotel hotel = new Hotel();
        hotel.setName(hotelForm.getName());
        hotel.setAddress(hotelForm.getAddress());
        hotel.setStar(hotelForm.getStar());
        hotel.setDescription(hotelForm.getDescription());
        hotel.setActive(true);
        hotel.setAvatar("images/hotel/"+fileName1);
        hotel.setImg1("images/hotel/"+fileName2);
        hotel.setImg2("images/hotel/"+fileName3);
        hotel.setImg3("images/hotel/"+fileName4);
        hotel.setImg4("images/hotel/"+fileName5);
        hotel.setUser(user);
        hotel.setCity(city);
        hotel.setServiceHotel(serviceHotel);
        hotelService.hotelRepository.save(hotel);

        ModelAndView modelAndView = new ModelAndView();
        HotelForm hotel1 = new HotelForm();
        modelAndView.addObject("hotel", hotel1);
        modelAndView.setViewName("add-hotel");
        return modelAndView;
    }

    @GetMapping(value = "/partner/hotel/detail/{id}")
    public ModelAndView detailHotel(@PathVariable Integer id){
        Hotel hotel = hotelService.hotelRepository.findById(id);
        HotelForm hotelForm = new HotelForm(hotel, "../../../");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hotel", hotelForm);
        modelAndView.setViewName("detail-hotel");
        return modelAndView;
    }

    @PostMapping(value = "/partner/hotel/detail/{id}")
    public String updateHotel(@Valid HotelForm hotelForm) {
        Hotel hotel = hotelService.hotelRepository.findById(hotelForm.getId());
        if(!hotel.getName().equals(hotelForm.getName())){
            hotelService.hotelRepository.updateName(hotel.getId(), hotelForm.getName());
        }

        if(!hotel.getAddress().equals(hotelForm.getAddress())){
            hotelService.hotelRepository.updateAddress(hotel.getId(), hotelForm.getAddress());
        }

        if(!hotel.getCity().getName().equals(hotelForm.getCity())){
            City city = cityService.cityRepository.findByName(hotelForm.getCity());
            hotelService.hotelRepository.updateCityId(hotel.getId(), city.getId());
        }

        ServiceHotel serviceHotel = serviceHotelService.serviceHotelRepository.findByAllAttr(
                hotelForm.isWifi(), hotelForm.isPool(), hotelForm.isGym(), hotelForm.isElevator(), hotelForm.isRestaurant());

        if(serviceHotel.getId()!=hotel.getServiceHotel().getId()){
            hotelService.hotelRepository.updateServiceHotelId(hotel.getId(), serviceHotel.getId());
        }

        if(!hotel.getDescription().equals(hotelForm.getDescription())){
            hotelService.hotelRepository.updateDescription(hotel.getId(), hotelForm.getDescription());
        }

        if(hotel.getStar()!=hotelForm.getStar()){
            hotelService.hotelRepository.updateStar(hotel.getId(), hotelForm.getStar());
        }

        MultipartFile multipartFile1 = hotelForm.getAvatar();
        String fileName1 = multipartFile1.getOriginalFilename();
        MultipartFile multipartFile2 = hotelForm.getImg1();
        String fileName2 = multipartFile2.getOriginalFilename();
        MultipartFile multipartFile3 = hotelForm.getImg2();
        String fileName3 = multipartFile3.getOriginalFilename();
        MultipartFile multipartFile4 = hotelForm.getImg3();
        String fileName4 = multipartFile4.getOriginalFilename();
        MultipartFile multipartFile5 = hotelForm.getImg4();
        String fileName5 = multipartFile5.getOriginalFilename();

        try {
            if(!("images/hotel/"+fileName1).equals(hotel.getAvatar())){
                FileCopyUtils.copy(hotelForm.getAvatar().getBytes(), new File(this.fileUpload + fileName1));
                hotelService.hotelRepository.updateAvatar(hotel.getId(), "images/hotel/"+fileName1);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!("images/hotel/"+fileName2).equals(hotel.getImg1())){
                FileCopyUtils.copy(hotelForm.getImg1().getBytes(), new File(this.fileUpload + fileName2));
                hotelService.hotelRepository.updateImg1(hotel.getId(), "images/hotel/"+fileName2);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!("images/hotel/"+fileName3).equals(hotel.getImg2())){
                FileCopyUtils.copy(hotelForm.getImg2().getBytes(), new File(this.fileUpload + fileName3));
                hotelService.hotelRepository.updateImg2(hotel.getId(), "images/hotel/"+fileName3);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!("images/hotel/"+fileName4).equals(hotel.getImg3())){
                FileCopyUtils.copy(hotelForm.getImg3().getBytes(), new File(this.fileUpload + fileName4));
                hotelService.hotelRepository.updateImg3(hotel.getId(), "images/hotel/"+fileName4);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!("images/hotel/"+fileName5).equals(hotel.getImg4())){
                FileCopyUtils.copy(hotelForm.getImg4().getBytes(), new File(this.fileUpload + fileName5));
                hotelService.hotelRepository.updateImg4(hotel.getId(), "images/hotel/"+fileName5);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/partner/hotel/detail/"+hotel.getId();
    }
}
