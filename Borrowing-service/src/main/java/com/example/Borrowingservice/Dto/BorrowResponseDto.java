package com.example.Borrowingservice.Dto;

import com.example.Borrowingservice.Model.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BorrowResponseDto {
    private String borrowingNumber;
    private LocalDate date;
    private String isbn;
    private String title;
    private String customerNumber;
    private String name;
}
