package com.example.tourweb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class SearchRequest {
    private String option;
    private String searchValue;
}
