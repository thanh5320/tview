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
public class UpdateUser {
    private MultipartFile fileImg;

    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String img;

    public UpdateUser(User user){
        id = user.getId();
        name = user.getName();
        email=user.getEmail();
        address = user.getAddress();
        phone = user.getPhone();
        img = user.getImg();
    }
}
