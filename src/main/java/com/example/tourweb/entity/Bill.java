package com.example.tourweb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Bill {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String whereTo;
    private int guests;
    private Date arrivalsDate;
    private Date leavingDate;
    private int totalMoney;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;

}
