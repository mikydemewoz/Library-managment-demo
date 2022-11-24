package com.example.Bookqueriesservice.Message;

import com.example.Bookqueriesservice.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @Autowired
    private BookService bookService;

    @KafkaListener(topics = "BookQueryAdd")
    public void BookReceiveToAdd(String book ){
        bookService.add(book);
    }
    @KafkaListener(topics = "BookQueryUpdate")
    public void BookReceiveToUpdate(String book ){
        bookService.update(book);
    }
    @KafkaListener(topics = "BookQueryDelete")
    public void BookReceiveToDelete(String isbn ){
        bookService.delete(isbn);
    }
    @KafkaListener(topics = "reviewEdit")
    public void BookReceiveToUpdateReview(String review ){
        System.out.println(review);
        bookService.updateReview(review);
    }
}
