package com.example.tourweb.model;

import lombok.*;

import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class AnswerFormRequest {
    private List<GameRequest> gameRequests;
}
