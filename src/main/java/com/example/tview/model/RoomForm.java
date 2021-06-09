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
public class RoomForm {
    private Integer id;

    private String name;

    private String description;

    private MultipartFile img1;
    public String strImg1;

    private MultipartFile img2;
    public String strImg2;

    private MultipartFile img3;
    public String strImg3;

    private MultipartFile img4;
    public String strImg4;

    private boolean active;

    private double cost;

    private int  mountOfPeople;

    public boolean getActive() {
        return active;
    }

    public RoomForm(Room room, String img){
        id=room.getId();
        name=room.getName();
        description=room.getDescription();
        strImg1=img+room.getImg1();
        strImg2=img+room.getImg2();
        strImg3=img+room.getImg3();
        strImg4=img+room.getImg4();
        cost=room.getCost();
        mountOfPeople=room.getMountOfPeople();
    }
}
