package com.example.tourweb.model;


import lombok.*;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String tel;
    private String gender;
    private Date dateOfBirth;
    private String country;
    private String inviteCode;
}
