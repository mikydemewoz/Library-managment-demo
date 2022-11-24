package com.example.Customerservice.Messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("updateBorrowing")
    private String topicUpdateBorrowing;

    public void sendToBorrowing(String customer){
        kafkaTemplate.send(topicUpdateBorrowing, customer);
    }
}
