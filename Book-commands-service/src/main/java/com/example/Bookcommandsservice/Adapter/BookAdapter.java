package com.example.Bookcommandsservice.Adapter;

import com.example.Bookcommandsservice.Dto.BookRequestDto;
import com.example.Bookcommandsservice.Dto.BookResponseDto;
import com.example.Bookcommandsservice.Model.Book;

import java.util.List;

public class BookAdapter {
    public List<BookResponseDto> BookListToBookResponseDto(List<Book> books) {
        return books.stream()
                .map(book -> new BookResponseDto(book.getIsbn(), book.getTitle(), book.getDescription(), book.getAuthorName()))
                .toList();
    }

    public Book BookRequestToBook(BookRequestDto bookRequestDto) {
        return Book.builder()
                .authorName(bookRequestDto.getAuthorName())
                .description(bookRequestDto.getDescription())
                .title(bookRequestDto.getTitle())
                .isbn(bookRequestDto.getIsbn())
                .build();
    }

    public BookResponseDto BookToBookResponseDto(Book book) {
        return BookResponseDto.builder()
                .authorName(book.getAuthorName())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .build();
    }

    public Book BookRequestDtoToBook(BookRequestDto bookRequestDto) {
        return Book.builder()
                .isbn(bookRequestDto.getIsbn())
                .title(bookRequestDto.getTitle())
                .description(bookRequestDto.getDescription())
                .authorName(bookRequestDto.getAuthorName())
                .build();
    }
}
