package com.example.Bookcommandsservice.Service;

import com.example.Bookcommandsservice.Adapter.BookAdapter;
import com.example.Bookcommandsservice.Dao.BookDao;
import com.example.Bookcommandsservice.Dto.BookRequestDto;
import com.example.Bookcommandsservice.Dto.BookResponseDto;
import com.example.Bookcommandsservice.Dto.BookResponseToBorrowingMsg;
import com.example.Bookcommandsservice.Messaging.Sender;
import com.example.Bookcommandsservice.Model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private Sender sender;
    private BookAdapter bookAdapter = new BookAdapter();
    private ObjectMapper mapper = new ObjectMapper();
    public List<BookResponseDto> getAll(){
        List<Book> books = bookDao.findAll();
        return bookAdapter.BookListToBookResponseDto(books);
    }

    public void add(BookRequestDto bookRequestDto){
        Book book = bookAdapter.BookRequestToBook(bookRequestDto);
        try{
            System.out.println("--------------Called---------------");
            String bookStr = mapper.writeValueAsString(bookRequestDto);
            bookDao.save(book);
            sender.sendToBookQueryAdd(bookStr);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public BookResponseDto getOne(String isbn) {
        Book book = bookDao.findByIsbn(isbn).get();
        System.out.println(book);
        BookResponseDto bookResponseDto = bookAdapter.BookToBookResponseDto(book);
        return bookResponseDto;
    }

    public void update(String isbn, BookRequestDto bookRequestDto) {
        Book book = bookDao.findByIsbn(isbn).get();
        Book bookFromBookDto = bookAdapter.BookRequestDtoToBook(bookRequestDto);
        book.setIsbn(bookFromBookDto.getIsbn() != null ? bookFromBookDto.getIsbn() : book.getIsbn());
        book.setTitle(bookFromBookDto.getTitle() != null ? bookFromBookDto.getTitle() : book.getTitle());
        book.setAuthorName(bookFromBookDto.getAuthorName() != null ? bookFromBookDto.getAuthorName() : book.getAuthorName());
        book.setDescription(bookFromBookDto.getDescription() != null ? bookFromBookDto.getDescription() : book.getDescription());

        Book book1 = bookDao.save(book);
        BookResponseToBorrowingMsg bookResponseToBorrowingMsg = new BookResponseToBorrowingMsg();
        bookResponseToBorrowingMsg.setIsbn(book.getIsbn());
        bookResponseToBorrowingMsg.setTitle(book.getTitle());
        String bookStr = null;
        String bookStr2 = null;
        try {
            bookStr = mapper.writeValueAsString(book1);
            bookStr2 = mapper.writeValueAsString(bookResponseToBorrowingMsg);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        sender.sendToBookQueryUpdate(bookStr);
        sender.sendToBorrowingUpdate(bookStr2);
    }

    public void delete(String isbn) {
        bookDao.deleteByIsbn(isbn);
        sender.sendToBookQueryDelete(isbn);
    }
}
