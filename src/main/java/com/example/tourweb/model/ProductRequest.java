package com.example.tourweb.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ProductRequest {

    private String name;

    private int price;

    private String description;

  //  private String imageUrl;
}
