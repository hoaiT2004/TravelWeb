package com.example.tourweb.model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class BillRequest {
    private String whereTo;
    private int guests;
    private Date arrivalsDate;
    private Date leavingDate;
    private int totalMoney;
}
