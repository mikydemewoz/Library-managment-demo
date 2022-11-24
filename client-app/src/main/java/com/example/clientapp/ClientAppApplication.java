package com.example.clientapp;

import com.example.clientapp.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication

public class ClientAppApplication implements CommandLineRunner {

	public RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(ClientAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String BookCreateUrl = "http://localhost:8080/api/bookcommand";
		String customerUrl = "http://localhost:8080/api/customer";
		String reviewUrl = "http://localhost:8080/api/review";
		String BookQueryUrl = "http://localhost:8080/api/bookquery";
		String borrowingUrl = "http://localhost:8080/api/borrow";

		CustomerRequestDto customerRequestDto1 = CustomerRequestDto.builder()
				.customerNumber("123")
				.name("Menge")
				.address(new Address("4th", "Addis Ababa", "12121"))
				.contact(new Contact("0911111111","menge@gmail.com"))
				.build();
		CustomerRequestDto customerRequestDto2 = CustomerRequestDto.builder()
				.customerNumber("122")
				.name("Doyo")
				.address(new Address("4th", "Addis Ababa", "3433"))
				.contact(new Contact("0911111232","doyo@gmail.com"))
				.build();

		restTemplate.postForObject(customerUrl, customerRequestDto1, Void.class);
		restTemplate.postForObject(customerUrl, customerRequestDto2, Void.class);
		System.out.println("--------------------Customer Added------------------------");

		BookRequestDto bookRequestDto1 = BookRequestDto.builder()
				.authorName("Michael")
				.isbn("123ab")
				.description("It is a book about Java")
				.title("Fundamental of Java")
				.build();
		BookRequestDto bookRequestDto2 = BookRequestDto.builder()
				.authorName("Kalkidan")
				.isbn("123abc")
				.description("It is a book about Python")
				.title("Fundamental of Python")
				.build();
		restTemplate.postForObject(BookCreateUrl, bookRequestDto1, Void.class);
		restTemplate.postForObject(BookCreateUrl, bookRequestDto2, Void.class);
		System.out.println("--------------------Book Added------------------------");

		ReviewRequestDto reviewRequestDto1 = ReviewRequestDto.builder()
				.isbn("123ab")
				.customerName("Menge")
				.description("This is my first review from Menge")
				.rating(3)
				.build();

		ReviewRequestDto reviewRequestDto2 = ReviewRequestDto.builder()
				.isbn("123abc")
				.customerName("Doyo")
				.description("This is my first review from Doyo")
				.rating(4)
				.build();
		restTemplate.postForObject(reviewUrl, reviewRequestDto1, Void.class);
		restTemplate.postForObject(reviewUrl, reviewRequestDto2, Void.class);
		System.out.println("--------------------Review Added------------------------");
//		Todo:Til this worked

		BookResponseDto[] bookResponseDto = restTemplate.getForObject(BookQueryUrl, BookResponseDto[].class);
		System.out.println("--------------------All the book lists------------------------");
		System.out.println(Arrays.toString(bookResponseDto));

		BorrowRequestDto borrowRequestDto = BorrowRequestDto.builder()
				.borrowingNumber("123")
				.customerNumber("122")
				.isbn("123ab")

				.build();
		restTemplate.postForObject(borrowingUrl, borrowRequestDto, Void.class);
		System.out.println("--------------------Borrowing added------------------------");

		BookRequestDto bookRequestDto = BookRequestDto.builder()
				.title("Fundamental of Java2")
				.build();
		restTemplate.put(BookCreateUrl+"/123ab", bookRequestDto, Void.class);
		System.out.println("--------------------Book updated------------------------");

		BookResponseDto[] bookResponseDto2 = restTemplate.getForObject(BookQueryUrl, BookResponseDto[].class);
		System.out.println("--------------------All the book lists after update------------------------");
		System.out.println(Arrays.toString(bookResponseDto2));

		BorrowResponseDto[] borrowResponseDto = restTemplate.getForObject(borrowingUrl, BorrowResponseDto[].class);
		System.out.println("--------------------All the book lists after update------------------------");
		System.out.println(Arrays.toString(borrowResponseDto));
	}

}
