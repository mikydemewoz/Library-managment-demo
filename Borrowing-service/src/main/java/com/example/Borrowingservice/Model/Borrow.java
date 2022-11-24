package com.example.Borrowingservice.Model;

import com.example.Borrowingservice.Dto.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Borrow {
    @Id
    private String id;
    private String borrowingNumber;
    private LocalDate date;
    private String isbn;
    private String title;
    private String customerNumber;
    private String name;
}
