package com.example.Bookqueriesservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookResponseFromCommand {
    private String isbn;
    private String title;
    private String description;
    private String authorName;
}
