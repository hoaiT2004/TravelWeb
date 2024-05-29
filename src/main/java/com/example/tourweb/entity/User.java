package com.example.tourweb.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Indexed;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Table(name = "user")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String tel;
    private String gender;
    private Date dateOfBirth;
    private String country;
    private String inviteCode;
    private int money;
    private String roles;
    private String linkAvatar;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Bill> bills;
}
