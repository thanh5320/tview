package com.example.tview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="service_hotel")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ServiceHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="s_h_id")
    private Integer id;

    @Column(name="wifi")
    private boolean wifi;

    @Column(name="pool")
    private boolean pool;

    @Column(name="gym")
    private boolean gym;

    @Column(name="elevator")
    private boolean elevator;

    @Column(name="restaurant")
    private boolean restaurant;
}
