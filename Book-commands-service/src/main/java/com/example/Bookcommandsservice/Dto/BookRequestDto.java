package com.example.Bookcommandsservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookRequestDto {
    private String isbn;
    private String title;
    private String description;
    private String authorName;
}
