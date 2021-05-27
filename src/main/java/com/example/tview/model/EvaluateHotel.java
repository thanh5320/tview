package com.example.tview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name="evaluate_hotel")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EvaluateHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eva_id")
    private Integer id;

    @Column(name="comment")
    private String comment;

    @Column(name="title")
    private String title;

    @Column(name="date_cmt")
    private Date date;

    @Column(name = "star")
    private int star;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="hotel_id", nullable=false)
    private Hotel hotel;




}