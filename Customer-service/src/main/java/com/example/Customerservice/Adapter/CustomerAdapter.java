package com.example.Customerservice.Adapter;

import com.example.Customerservice.Dto.CustomerRequestDto;
import com.example.Customerservice.Dto.CustomerResponseDto;
import com.example.Customerservice.Model.Customer;

import java.util.List;

public class CustomerAdapter {
    public List<CustomerResponseDto> CustomerListToCustomerResponseDto(List<Customer> customers){
        return customers.stream()
                .map(customer -> new CustomerResponseDto(customer.getCustomerNumber(), customer.getName(), customer.getContact(), customer.getAddress()))
                .toList();
    }

    public Customer CustomerRequestToCustomer(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .customerNumber(customerRequestDto.getCustomerNumber())
                .contact(customerRequestDto.getContact())
                .address(customerRequestDto.getAddress())
                .name(customerRequestDto.getName())
                .build();
    }

    public CustomerResponseDto CustomerToCustomerResponseDto(Customer customer) {
        return new CustomerResponseDto(customer.getCustomerNumber(), customer.getName(), customer.getContact(), customer.getAddress());
    }

    public Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto) {
        return Customer.builder()
                .name(customerRequestDto.getName())
                .customerNumber(customerRequestDto.getCustomerNumber())
                .address(customerRequestDto.getAddress())
                .contact(customerRequestDto.getContact())
                .build();
    }
}
