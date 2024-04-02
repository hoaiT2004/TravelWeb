package com.example.tourweb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ChangePasswordRequest {

    private String username;
    private String password;
    private String newPassword;
    private String againNewPassword;

}
