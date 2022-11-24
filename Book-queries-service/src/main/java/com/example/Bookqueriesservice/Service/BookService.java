package com.example.Bookqueriesservice.Service;

import com.example.Bookqueriesservice.Adapter.BookAdapter;
import com.example.Bookqueriesservice.Dao.BookDao;
import com.example.Bookqueriesservice.Dto.BookResponseDto;
import com.example.Bookqueriesservice.Dto.BookResponseFromCommand;
import com.example.Bookqueriesservice.Dto.ReviewRequestDto;
import com.example.Bookqueriesservice.Model.Book;
import com.example.Bookqueriesservice.Model.Review;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    private BookAdapter bookAdapter = new BookAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    public List<BookResponseDto> getAll() {
        System.out.println("hey hey");
        List<Book> books = bookDao.findAll();
        List<BookResponseDto> bookResponseDto = bookAdapter.BookToBookResponseDto(books);
        return bookResponseDto;
    }

    public void add(String book){
        try{
            BookResponseFromCommand bookResponseFromCommand = mapper.readValue(book, BookResponseFromCommand.class);
            Book book1 = bookAdapter.BookResponseFromCommandToBook(bookResponseFromCommand);
            bookDao.save(book1);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void update(String book){
        try{
            Book book1 = mapper.readValue(book, Book.class);
            bookDao.save(book1);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void delete(String isbn){
        bookDao.deleteByIsbn(isbn);
    }

    public void updateReview(String review){
        try{
            ReviewRequestDto reviewRequestDto = mapper.readValue(review, ReviewRequestDto.class);
            Book book = bookDao.findByIsbn(reviewRequestDto.getIsbn()).get();
            List<Review> review1 = book.getReviews();
            if(review1 == null){
                review1 = new ArrayList<>();
            }
            Review review2 = bookAdapter.ReviewRequestDtoToReview(reviewRequestDto);
            review1.add(review2);
            book.setReviews(review1);
            bookDao.save(book);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
