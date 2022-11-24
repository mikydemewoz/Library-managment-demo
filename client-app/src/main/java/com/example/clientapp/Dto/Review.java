package com.example.clientapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Review {
    private String isbn;
    private Integer rating;
    private String description;
    private String customerName;
}
