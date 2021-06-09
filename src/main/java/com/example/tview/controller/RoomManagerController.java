package com.example.tview.controller;

import com.example.tview.model.*;
import com.example.tview.service.*;
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
public class RoomManagerController {
    private String fileUpload="C:\\Users\\NGUYEN VAN THANH\\Desktop\\THANH\\tview\\tview\\src\\main\\resources\\static\\images\\room\\";
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ServiceHotelService serviceHotelService;

    @GetMapping(value = "/partner/hotel/{id}/rooms")
    public ModelAndView room(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Hotel hotel = hotelService.hotelRepository.findById(id);
        modelAndView.addObject("hotel", hotel);

        List<Room> rooms = roomService.roomRepository.findByHotelId(id);
        modelAndView.addObject("rooms", rooms);

        modelAndView.setViewName("room-manager");
        return modelAndView;
    }

    @PutMapping(value = "/partner/disable/room/{id}")
    public ResponseEntity<Integer> disableRoom(@PathVariable Integer id) {
        boolean isRemoved = roomService.disable(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/partner/able/room/{id}")
    public ResponseEntity<Integer> ableHotel(@PathVariable Integer id) {
        boolean isRemoved = roomService.able(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value="/partner/hotel/{id}/room/add")
    public ModelAndView addRoom(@PathVariable Integer id){
        Hotel hotel = hotelService.hotelRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        RoomForm room = new RoomForm();
        modelAndView.addObject("room", room);
        modelAndView.addObject("hotel", hotel);
        modelAndView.setViewName("add-room");
        return modelAndView;
    }


    @PostMapping(value="/partner/hotel/{id}/room/add")
    public ModelAndView createHotel(@PathVariable Integer id,@Valid RoomForm roomForm) {

        MultipartFile multipartFile1 = roomForm.getImg1();
        String fileName1 = multipartFile1.getOriginalFilename();
        MultipartFile multipartFile2 = roomForm.getImg2();
        String fileName2 = multipartFile2.getOriginalFilename();
        MultipartFile multipartFile3 = roomForm.getImg3();
        String fileName3 = multipartFile3.getOriginalFilename();
        MultipartFile multipartFile4 = roomForm.getImg4();
        String fileName4 = multipartFile4.getOriginalFilename();

        try {
            FileCopyUtils.copy(roomForm.getImg1().getBytes(), new File(this.fileUpload + fileName1));
            FileCopyUtils.copy(roomForm.getImg2().getBytes(), new File(this.fileUpload + fileName2));
            FileCopyUtils.copy(roomForm.getImg3().getBytes(), new File(this.fileUpload + fileName3));
            FileCopyUtils.copy(roomForm.getImg4().getBytes(), new File(this.fileUpload + fileName4));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Room room = new Room();
        room.setName(roomForm.getName());
        room.setActive(true);
        room.setImg1("images/room/"+fileName1);
        room.setImg2("images/room/"+fileName2);
        room.setImg3("images/room/"+fileName3);
        room.setImg4("images/room/"+fileName4);
        room.setCost(roomForm.getCost());
        room.setDescription(roomForm.getDescription());
        room.setMountOfPeople(roomForm.getMountOfPeople());

        Hotel hotel=hotelService.hotelRepository.findById(id);
        room.setHotel(hotel);

        roomService.roomRepository.save(room);

        ModelAndView modelAndView = new ModelAndView();
        RoomForm room1= new RoomForm();
        modelAndView.addObject("room", room1);
        modelAndView.addObject("hotel" , hotel);
        modelAndView.setViewName("add-room");
        return modelAndView;
    }

    @GetMapping(value = "/partner/room/detail/{id}")
    public ModelAndView detailRoom(@PathVariable Integer id){
        Room room = roomService.roomRepository.findById(id);
        RoomForm roomForm = new RoomForm(room, "../../../../");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hotel", room.getHotel());
        modelAndView.addObject("room", roomForm);
        modelAndView.setViewName("detail-room");
        return modelAndView;
    }

    @PostMapping(value = "/partner/room/detail/{id}")
    public String updateRoom(@Valid RoomForm roomForm) {
        Room room = roomService.roomRepository.findById(roomForm.getId());
        if(!room.getName().equals(roomForm.getName())){
            roomService.roomRepository.updateName(room.getId(), roomForm.getName());
        }

        if(!room.getDescription().equals(roomForm.getDescription())){
            roomService.roomRepository.updateDescription(room.getId(), roomForm.getDescription());
        }

        if(room.getCost()!=roomForm.getCost()){
            roomService.roomRepository.updateCost(room.getId(), roomForm.getCost());
        }

        if(room.getMountOfPeople()!=roomForm.getMountOfPeople()){
            roomService.roomRepository.updateNum(room.getId(), roomForm.getMountOfPeople());
        }

        MultipartFile multipartFile1 = roomForm.getImg1();
        String fileName1 = multipartFile1.getOriginalFilename();
        MultipartFile multipartFile2 = roomForm.getImg2();
        String fileName2 = multipartFile2.getOriginalFilename();
        MultipartFile multipartFile3 = roomForm.getImg3();
        String fileName3 = multipartFile3.getOriginalFilename();
        MultipartFile multipartFile4 = roomForm.getImg4();
        String fileName4 = multipartFile4.getOriginalFilename();

        try {
            if(!("images/room/"+fileName1).equals(room.getImg1())){
                FileCopyUtils.copy(roomForm.getImg1().getBytes(), new File(this.fileUpload + fileName1));
                roomService.roomRepository.updateImg1(room.getId(), "images/room/"+fileName1);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!("images/room/"+fileName2).equals(room.getImg2())){
                FileCopyUtils.copy(roomForm.getImg2().getBytes(), new File(this.fileUpload + fileName2));
                roomService.roomRepository.updateImg2(room.getId(), "images/room/"+fileName2);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!("images/room/"+fileName3).equals(room.getImg3())){
                FileCopyUtils.copy(roomForm.getImg3().getBytes(), new File(this.fileUpload + fileName3));
                roomService.roomRepository.updateImg3(room.getId(), "images/room/"+fileName3);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!("images/room/"+fileName4).equals(room.getImg4())){
                FileCopyUtils.copy(roomForm.getImg4().getBytes(), new File(this.fileUpload + fileName4));
                roomService.roomRepository.updateImg4(room.getId(), "images/room/"+fileName4);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/partner/room/detail/"+room.getId();
    }
}
