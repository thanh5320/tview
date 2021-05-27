package com.example.tview.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="room")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
    private Integer id;

    @Column(name="name")
    private String name;

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

    @Column(name="cost")
    private double cost;

    @Column(name="mount_of_people")
    private int  mountOfPeople;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;
}
