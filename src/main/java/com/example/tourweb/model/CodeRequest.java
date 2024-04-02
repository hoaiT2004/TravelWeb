package com.example.tourweb.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class CodeRequest {

    private String username;
    private String email;
    private String codeTrue;
    private String code;
    private LocalDateTime sendCodeDateTime;
    private LocalDateTime enterCodeDatetime;
}
