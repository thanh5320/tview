package com.example.tview.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name="city")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private Integer id;

    @Column(name="name")
    private String name;
}
