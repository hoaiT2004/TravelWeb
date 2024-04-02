package com.example.tourweb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class GameRequest {
    private String category;
    private String question;
    private String encodeQuestion;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private String answer;
    private String answerRight;
    private int receiveMoney;
}
