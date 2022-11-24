package com.example.Bookqueriesservice.Adapter;

import com.example.Bookqueriesservice.Dto.BookResponseDto;
import com.example.Bookqueriesservice.Dto.BookResponseFromCommand;
import com.example.Bookqueriesservice.Dto.ReviewRequestDto;
import com.example.Bookqueriesservice.Model.Book;
import com.example.Bookqueriesservice.Model.Review;

import java.util.List;

public class BookAdapter {
    public List<BookResponseDto> BookToBookResponseDto(List<Book> books) {
        return books.stream()
                .map(book -> new BookResponseDto(book.getTitle(),book.getDescription(),book.getAuthorName(), book.getReviews()))
                .toList();
    }

    public Book BookResponseFromCommandToBook(BookResponseFromCommand bookResponseFromCommand){
        return Book.builder()
                .authorName(bookResponseFromCommand.getAuthorName())
                .description(bookResponseFromCommand.getDescription())
                .title(bookResponseFromCommand.getTitle())
                .isbn(bookResponseFromCommand.getIsbn())
                .build();
    }

    public Review ReviewRequestDtoToReview(ReviewRequestDto reviewRequestDto){
        return Review.builder()
                .customerName(reviewRequestDto.getCustomerName())
                .description(reviewRequestDto.getDescription())
                .isbn(reviewRequestDto.getIsbn())
                .rating(reviewRequestDto.getRating())
                .build();
    }
}
