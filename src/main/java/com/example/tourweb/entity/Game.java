package com.example.tourweb.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    private String question;
    private String encodeQuestion;
    private String category;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private String answerRight;
    private int receiveMoney;
}
