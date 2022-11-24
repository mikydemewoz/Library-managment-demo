package com.example.Bookcommandsservice.Controller;

import com.example.Bookcommandsservice.Dto.BookRequestDto;
import com.example.Bookcommandsservice.Dto.BookResponseDto;
import com.example.Bookcommandsservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookcommand")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAll(){
        System.out.println("here");
        List<BookResponseDto> bookServiceResponse = bookService.getAll();
        return new ResponseEntity<>(bookServiceResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody BookRequestDto bookRequestDto){
        System.out.println(bookRequestDto);
        bookService.add(bookRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDto> getOne(@PathVariable String isbn){
        return new ResponseEntity<>(bookService.getOne(isbn), HttpStatus.OK);
    }
    @PutMapping("/{isbn}")
    public ResponseEntity<?> update(@PathVariable String isbn,@RequestBody BookRequestDto bookRequestDto){
        bookService.update(isbn, bookRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> delete(@PathVariable String isbn){
        bookService.delete(isbn);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
