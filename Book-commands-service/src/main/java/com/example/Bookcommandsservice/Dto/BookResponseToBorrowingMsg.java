package com.example.Bookcommandsservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookResponseToBorrowingMsg {
    private String isbn;
    private String title;
}
