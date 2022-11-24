package com.example.Reviewservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewResponseDto {
    private String isbn;
    private Integer rating;
    private String description;
    private String customerName;
}
