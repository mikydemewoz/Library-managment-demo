package com.example.Borrowingservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BorrowRequestDto {
    private String borrowingNumber;
    private String isbn;
    private String customerNumber;
}
