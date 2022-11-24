package com.example.Bookqueriesservice.Controller;

import com.example.Bookqueriesservice.Dto.BookResponseDto;
import com.example.Bookqueriesservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookquery")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookResponseDto> getAll(){
        return bookService.getAll();
    }
}
