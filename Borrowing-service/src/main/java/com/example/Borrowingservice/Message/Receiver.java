package com.example.Borrowingservice.Message;

import com.example.Borrowingservice.Service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @Autowired
    private BorrowService borrowService;

    @KafkaListener(topics = "updateBorrowing")
    public void CustomerReceivedToUpdateBorrowing(String customer ){
        System.out.println(customer);
        borrowService.updateCustomer(customer);
    }

    @KafkaListener(topics = "BorrowingUpdate")
    public void BookReceivedToUpdateBorrowing(String book ){
        System.out.println(book);
        borrowService.updateBook(book);
    }
}
