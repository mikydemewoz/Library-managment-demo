package com.example.Reviewservice.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("reviewEdit")
    private String topicAdd;

    public void sendToBookQueryAdd(String review){
        kafkaTemplate.send(topicAdd, review);
    }
}
