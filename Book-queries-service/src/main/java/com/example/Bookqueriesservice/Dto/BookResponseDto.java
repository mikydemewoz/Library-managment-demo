package com.example.Bookqueriesservice.Dto;

import com.example.Bookqueriesservice.Model.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookResponseDto {
    private String title;
    private String description;
    private String authorName;
    private List<Review> reviews;
}
