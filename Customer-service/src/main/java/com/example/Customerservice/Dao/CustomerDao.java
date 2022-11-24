package com.example.Customerservice.Dao;

import com.example.Customerservice.Model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerDao  extends MongoRepository<Customer, String> {
    Optional<Customer> findByCustomerNumber(String customerNumber);
    void deleteByCustomerNumber(String customerNumber);
}
