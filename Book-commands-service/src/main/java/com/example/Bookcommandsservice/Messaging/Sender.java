package com.example.Bookcommandsservice.Messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("BookQueryAdd")
    private String topicAdd;
    @Value("BookQueryUpdate")
    private String topicUpdate;
    @Value("BookQueryDelete")
    private String topicDelete;

    @Value("BorrowingUpdate")
    private String topicToBorrowingUpdate;

    public void sendToBookQueryAdd(String book){
        kafkaTemplate.send(topicAdd, book);
    }
    public void sendToBookQueryUpdate(String book){
        kafkaTemplate.send(topicUpdate, book);
    }
    public void sendToBookQueryDelete(String isbn){
        kafkaTemplate.send(topicDelete, isbn);
    }
    public void sendToBorrowingUpdate(String bookResponseMsgToBorrowing){
        System.out.println(bookResponseMsgToBorrowing);
        kafkaTemplate.send(topicToBorrowingUpdate, bookResponseMsgToBorrowing);
    }
}
