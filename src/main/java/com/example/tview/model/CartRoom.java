package com.example.tview.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name="cart_room")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="room_id", nullable=false)
    private Room room;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name="amount")
    private int amount;

    @Column(name="date_cart")
    private Date date;
}