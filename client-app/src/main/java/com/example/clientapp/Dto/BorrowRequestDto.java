package com.example.clientapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BorrowRequestDto {
    private String borrowingNumber;
    private String isbn;
    private String customerNumber;
}
