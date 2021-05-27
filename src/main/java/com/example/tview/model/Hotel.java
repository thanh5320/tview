package com.example.tview.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="hotel")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="hotel_id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="address")
    private String address;

    @Column(name="avatar")
    private String avatar;

    @Column(name="img1")
    private String img1;

    @Column(name="img2")
    private String img2;

    @Column(name="img3")
    private String img3;

    @Column(name="img4")
    private String img4;

    @Column(name="approve")
    private boolean appvore;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="s_h_id", nullable=false)
    private ServiceHotel serviceHotel;

    @ManyToOne
    @JoinColumn(name="city_id", nullable=false)
    private City city;

    private int star;

    private int cost;
}
