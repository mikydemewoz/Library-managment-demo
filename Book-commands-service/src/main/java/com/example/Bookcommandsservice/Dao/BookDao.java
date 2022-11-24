package com.example.Bookcommandsservice.Dao;

import com.example.Bookcommandsservice.Model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookDao extends MongoRepository<Book, String> {
    Optional<Book> findByIsbn(String isbn);
    void deleteByIsbn(String isbn);
}
