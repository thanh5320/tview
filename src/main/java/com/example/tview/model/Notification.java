package com.example.tview.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Table(name="notification")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="no_id")
    private Integer id;

    @Column(name="content")
    private String content;

    @Column(name="date_no")
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}