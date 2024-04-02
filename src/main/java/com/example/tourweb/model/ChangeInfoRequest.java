package com.example.tourweb.model;


import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ChangeInfoRequest {
    private String username;
    private String fullName;
    private String gender;
    private Date dateOfBirth;
    private String tel;
    private String country;
}
