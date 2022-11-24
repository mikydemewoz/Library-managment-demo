package com.example.Borrowingservice.Dao;

import com.example.Borrowingservice.Model.Borrow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BorrowDao extends MongoRepository<Borrow, String> {
    Optional<Borrow> findByBorrowingNumber(String borrowingNumber);
    Optional<Borrow> findByCustomerNumber(String borrowingNumber);
    Optional<Borrow> findByIsbn(String isbn);

    void deleteByBorrowingNumber(String borrowingNumber);
}
