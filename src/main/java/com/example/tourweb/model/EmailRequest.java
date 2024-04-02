package com.example.tourweb.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class EmailRequest {
    private String username;
    private String email;
}
