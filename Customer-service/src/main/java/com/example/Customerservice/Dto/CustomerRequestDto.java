package com.example.Customerservice.Dto;

import com.example.Customerservice.Model.Address;
import com.example.Customerservice.Model.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CustomerRequestDto {
    private String customerNumber;
    private String name;
    private Contact contact;
    private Address address;
}
