package com.example.tview.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelForm {
    private Integer id;

    private String name;

    private String description;

    private String address;

    private MultipartFile avatar;
    private String strAvatar;

    private MultipartFile img1;
    public String strImg1;

    private MultipartFile img2;
    public String strImg2;

    private MultipartFile img3;
    public String strImg3;

    private MultipartFile img4;
    public String strImg4;

    private boolean active;

    private boolean wifi;
    private boolean pool;
    private boolean restaurant;
    private boolean gym;
    private boolean elevator;

    private String city;

    private int star;

    public boolean getActive() {
        return active;
    }

    public HotelForm(Hotel hotel, String img){
        id=hotel.getId();
        name=hotel.getName();
        description=hotel.getDescription();
        address=hotel.getAddress();
        strAvatar=img+hotel.getAvatar();
        strImg1=img+hotel.getImg1();
        strImg2=img+hotel.getImg2();
        strImg3=img+hotel.getImg3();
        strImg4=img+hotel.getImg4();
        city=hotel.getCity().getName();
        wifi=hotel.getServiceHotel().isWifi();
        pool=hotel.getServiceHotel().isPool();
        gym=hotel.getServiceHotel().isGym();
        elevator=hotel.getServiceHotel().isElevator();
        restaurant=hotel.getServiceHotel().isRestaurant();
        star=hotel.getStar();
    }
}
