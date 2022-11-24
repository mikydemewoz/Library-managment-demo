package com.example.Borrowingservice.Service;

import com.example.Borrowingservice.Adapter.BorrowAdapter;
import com.example.Borrowingservice.Dao.BorrowDao;
import com.example.Borrowingservice.Dto.*;
import com.example.Borrowingservice.Model.Book;
import com.example.Borrowingservice.Model.Borrow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private BorrowDao borrowDao;
    @Autowired
    private BookFeignClient bookFeignClient;
    @Autowired
    private CustomerFeignClient customerFeignClient;
    private ObjectMapper mapper = new ObjectMapper();
    private BorrowAdapter borrowAdapter = new BorrowAdapter();
    public List<BorrowResponseDto> getAll() {
        List<Borrow> borrows = borrowDao.findAll();
        return borrowAdapter.BorrowListToBorrowResponseDto(borrows);
    }

    public void add(BorrowRequestDto borrowRequestDto) {
        Borrow borrow = borrowAdapter.BorrowRequestToBorrow(borrowRequestDto);
        BookResponseDto bookResponseDto = bookFeignClient.getBook(borrowRequestDto.getIsbn());
        Book book = borrowAdapter.BookResponseDtoToBook(bookResponseDto);
        borrow.setIsbn(book.getIsbn());
        borrow.setTitle(book.getTitle());
        CustomerResponseDto customerResponseDto = customerFeignClient.getCustomer(borrowRequestDto.getCustomerNumber());
        borrow.setCustomerNumber(customerResponseDto.getCustomerNumber());
        borrow.setName(customerResponseDto.getName());
        borrowDao.save(borrow);
    }

    public BorrowResponseDto getOne(String borrowingNumber) {
        Borrow borrow = borrowDao.findByBorrowingNumber(borrowingNumber).get();
        BorrowResponseDto borrowResponseDto = borrowAdapter.BorrowToBorrowResponseDto(borrow);
        return borrowResponseDto;
    }

//    Todo: this must be messaging
    public void update(String borrowingNumber, BorrowRequestDto borrowRequestDto) {
        Borrow borrow = borrowDao.findByBorrowingNumber(borrowingNumber).get();
//        Borrow borrowFromBorrowDto = borrowAdapter.BorrowRequestDtoToBorrow(borrowRequestDto);
        borrow.setDate(LocalDate.now());
//        borrow.setTitle(borrowFromBorrowDto.getTitle() != null ? borrowFromBorrowDto.getTitle() : borrow.getTitle());
//        borrow.setIsbn(borrowFromBorrowDto.getIsbn() != null ? borrowFromBorrowDto.getIsbn() : borrow.getIsbn());
//        borrow.setReturnDate(borrowFromBorrowDto.getReturnDate() != null ? borrowFromBorrowDto.getReturnDate() : borrow.getReturnDate());
//        borrow.setBorrowingNumber(borrowFromBorrowDto.getBorrowingNumber() != null ? borrowFromBorrowDto.getBorrowingNumber() : borrow.getBorrowingNumber());

        borrowDao.save(borrow);
    }

    public void updateCustomer(String customer){
        try{
            CustomerMsgReceived customerMsgReceived = mapper.readValue(customer, CustomerMsgReceived.class);
            Borrow borrow = borrowDao.findByCustomerNumber(customerMsgReceived.getCustomerNumber()).get();
            borrow.setName(customerMsgReceived.getName());
            borrowDao.save(borrow);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void delete(String borrowingNumber) {
        borrowDao.deleteByBorrowingNumber(borrowingNumber);
    }

    public void updateBook(String book) {
        try{
            BookMsgReceived bookMsgReceived = mapper.readValue(book, BookMsgReceived.class);
            System.out.println(bookMsgReceived);
            Borrow borrow = borrowDao.findByIsbn(bookMsgReceived.getIsbn()).get();
            System.out.println("Borrow-------"+borrow);
            borrow.setTitle(bookMsgReceived.getTitle());
            borrowDao.save(borrow);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FeignClient("Book-command-service") interface BookFeignClient {
    @RequestMapping("/api/bookcommand/{isbn}")
    BookResponseDto getBook(@PathVariable String isbn); }

    @FeignClient("Customer-service") interface CustomerFeignClient {
    @RequestMapping("/api/customer/{customerId}")
    CustomerResponseDto getCustomer(@PathVariable String customerId); }
}
