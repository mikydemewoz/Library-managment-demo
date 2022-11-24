package com.example.Customerservice.Controller;

import com.example.Customerservice.Dto.CustomerRequestDto;
import com.example.Customerservice.Dto.CustomerResponseDto;
import com.example.Customerservice.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAll(){
        System.out.println("here");
        List<CustomerResponseDto> customerServiceResponse = customerService.getAll();
        return new ResponseEntity<>(customerServiceResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> add(@RequestBody CustomerRequestDto customerRequestDto){
        System.out.println(customerRequestDto);
        customerService.add(customerRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDto> getOne(@PathVariable String customerId){
        return new ResponseEntity<>(customerService.getOne(customerId), HttpStatus.OK);
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<?> update(@PathVariable String customerId,@RequestBody CustomerRequestDto customerRequestDto){
        customerService.update(customerId, customerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> delete(@PathVariable String customerId){
        customerService.delete(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
