package com.example.clientapp.Dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class BorrowResponseDto {
    private String borrowingNumber;
    private LocalDate date;
    private String isbn;
    private String title;
    private String customerNumber;
    private String name;
}
