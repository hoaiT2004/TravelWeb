package com.example.tourweb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class LoginRequest {

    private String username;
    private String password;
}
