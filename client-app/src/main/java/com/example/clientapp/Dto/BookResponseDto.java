package com.example.clientapp.Dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class BookResponseDto {
    private String title;
    private String description;
    private String authorName;
    private List<Review> reviews;
}