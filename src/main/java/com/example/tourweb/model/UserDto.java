package com.example.tourweb.model;

import com.example.tourweb.entity.Bill;
import com.example.tourweb.entity.User;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String tel;
    private String gender;
    private Date dateOfBirth;
    private String country;
    private String code;
    private int money;
    private String roles;
    private Set<Bill> bills;

    public UserDto(User user){
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        fullname = user.getFullName();
        tel = user.getTel();
        gender = user.getGender();
        dateOfBirth = user.getDateOfBirth();
        country = user.getCountry();
        code = user.getInviteCode();
        money = user.getMoney();
        roles = user.getRoles();
        bills = user.getBills();
    }
}
