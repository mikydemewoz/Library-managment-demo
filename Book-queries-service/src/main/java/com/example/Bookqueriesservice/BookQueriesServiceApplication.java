package com.example.Bookqueriesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableKafka
@EnableScheduling
@EnableEurekaClient
public class BookQueriesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookQueriesServiceApplication.class, args);
	}

}
