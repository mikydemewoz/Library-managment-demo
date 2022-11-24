package com.example.Bookcommandsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@EnableEurekaClient
public class BookCommandsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCommandsServiceApplication.class, args);
	}

}
