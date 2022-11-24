package com.example.Borrowingservice.Adapter;

import com.example.Borrowingservice.Dto.*;
import com.example.Borrowingservice.Model.Book;
import com.example.Borrowingservice.Model.Borrow;

import java.time.LocalDate;
import java.util.List;

public class BorrowAdapter {
    public List<BorrowResponseDto> BorrowListToBorrowResponseDto(List<Borrow> borrows) {
        return borrows.stream()
                .map(borrow -> new BorrowResponseDto(borrow.getBorrowingNumber(), borrow.getDate(), borrow.getIsbn(), borrow.getTitle(), borrow.getCustomerNumber(), borrow.getName()))
                .toList();
    }

    public Borrow BorrowRequestToBorrow(BorrowRequestDto borrowRequestDto) {
        return Borrow.builder()
                .borrowingNumber(borrowRequestDto.getBorrowingNumber())
                .date(LocalDate.now())
//                .customerNumber(borrowRequestDto.getCustomerNumber())
                .build();
    }

    public BorrowResponseDto BorrowToBorrowResponseDto(Borrow borrow) {
        return BorrowResponseDto.builder()
                .borrowingNumber(borrow.getBorrowingNumber())
                .date(borrow.getDate())
                .customerNumber(borrow.getCustomerNumber())
                .name(borrow.getName())
                .isbn(borrow.getIsbn())
                .title(borrow.getTitle())
                .build();
    }

//    public Borrow BorrowRequestDtoToBorrow(BorrowRequestDto borrowRequestDto) {
//        return Borrow.builder()
//                .borrowingNumber(borrowRequestDto.getBorrowingNumber())
////                .borrowDate(borrowRequestDto.getBorrowDate())
////                .returnDate(borrowRequestDto.getReturnDate())
////                .isbn(borrowRequestDto.getIsbn())
////                .title(borrowRequestDto.getTitle())
//                .build();
//    }

    public Book BookResponseDtoToBook(BookResponseDto bookResponseDto) {
        return Book.builder()
                .authorName(bookResponseDto.getAuthorName())
                .description(bookResponseDto.getDescription())
                .isbn(bookResponseDto.getIsbn())
                .title(bookResponseDto.getTitle())
                .build();
    }


}
